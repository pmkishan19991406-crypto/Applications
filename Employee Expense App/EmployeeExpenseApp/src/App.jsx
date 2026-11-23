
import { useState } from 'react';
import './App.css';

const API = 'http://localhost:8080';

// COMMON API FUNCTION
async function apiRequest(url, method, body, token) {

  const headers = {
    'Content-Type': 'application/json'
  };

  if (token) {
    headers.Authorization = 'Bearer ' + token;
  }

  const response = await fetch(API + url, {
    method: method,
    headers: headers,
    body: body ? JSON.stringify(body) : undefined
  });

  const text = await response.text();

  let data = {};

  if (text) {
    try {
      data = JSON.parse(text);
    } catch {
      data = { message: text };
    }
  }

  if (!response.ok) {
    throw new Error(data.message || 'Something went wrong');
  }

  return data;
}

function App() {

  const [page, setPage] = useState('login');

  const [currentUser, setCurrentUser] = useState(null);
  const [token, setToken] = useState('');

  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const [expenses, setExpenses] = useState([]);
  const [employees, setEmployees] = useState([]);

  const [title, setTitle] = useState('');
  const [amount, setAmount] = useState('');
  const [category, setCategory] = useState('Food');

  const [selectedEmployee, setSelectedEmployee] = useState('');

  // LOAD DASHBOARD DATA
  async function loadData(role, jwt) {

    try {

      if (role === 'MANAGER') {

        const expenseData = await apiRequest(
          '/api/expenses/all',
          'GET',
          null,
          jwt
        );

        setExpenses(expenseData);

        const employeeData = await apiRequest(
          '/api/users/employees',
          'GET',
          null,
          jwt
        );

        setEmployees(employeeData);

      } else {

        const expenseData = await apiRequest(
          '/api/expenses/my',
          'GET',
          null,
          jwt
        );

        setExpenses(expenseData);
      }

    } catch (error) {
      alert(error.message);
    }
  }

  // SIGNUP
  async function signup(e) {

    e.preventDefault();

    try {

      const data = await apiRequest(
        '/api/users/register',
        'POST',
        {
          name: name,
          email: email,
          password: password
        },
        null
      );

      alert('Registration successful! Your role is ' + data.role);

      setName('');
      setEmail('');
      setPassword('');

      setPage('login');

    } catch (error) {
      alert(error.message);
    }
  }

  // LOGIN
  async function login(e) {

    e.preventDefault();

    try {

      const data = await apiRequest(
        '/api/users/login',
        'POST',
        {
          email: email,
          password: password
        },
        null
      );

      const user = {
        name: data.name,
        email: data.email,
        role: data.role
      };

      setCurrentUser(user);
      setToken(data.token);

      setEmail('');
      setPassword('');

      await loadData(data.role, data.token);

      setPage('dashboard');

      alert('Welcome ' + data.name);

    } catch (error) {
      alert(error.message);
    }
  }

  // SUBMIT EXPENSE
  async function submitExpense(e) {

    e.preventDefault();

    if (!title || !amount || Number(amount) <= 0) {
      alert('Enter valid expense details');
      return;
    }

    try {

      await apiRequest(
        '/api/expenses/submit',
        'POST',
        {
          title: title,
          amount: Number(amount),
          category: category
        },
        token
      );

      alert('Expense submitted successfully');

      setTitle('');
      setAmount('');
      setCategory('Food');

      loadData('EMPLOYEE', token);

    } catch (error) {
      alert(error.message);
    }
  }

  // APPROVE OR REJECT EXPENSE
  async function updateStatus(id, status) {

    try {

      await apiRequest(
        '/api/expenses/' + id + '/status?status=' + status,
        'PUT',
        null,
        token
      );

      alert('Expense ' + status.toLowerCase() + ' successfully');

      loadData('MANAGER', token);

    } catch (error) {
      alert(error.message);
    }
  }

  // CHANGE MANAGER
  async function changeManager() {

    if (!selectedEmployee) {
      alert('Please select an employee');
      return;
    }

    const confirmChange = window.confirm(
      'Are you sure you want to transfer manager role?'
    );

    if (!confirmChange) {
      return;
    }

    try {

      await apiRequest(
        '/api/users/change-manager/' + selectedEmployee,
        'PUT',
        null,
        token
      );

      alert('Manager changed successfully. Please login again.');

      logout();

    } catch (error) {
      alert(error.message);
    }
  }

  // LOGOUT
  function logout() {

    setCurrentUser(null);
    setToken('');

    setExpenses([]);
    setEmployees([]);

    setSelectedEmployee('');

    setPage('login');
  }

  return (

    <div className="app">

      {/* LOGIN PAGE */}

      {page === 'login' && (

        <div className="auth-box">

          <h1>Expense Tracker</h1>
          <h2>Login</h2>

          <form onSubmit={login}>

            <input
              type="email"
              placeholder="Email"
              value={email}
              onChange={e => setEmail(e.target.value)}
              required
            />

            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={e => setPassword(e.target.value)}
              required
            />

            <button type="submit">Login</button>

          </form>

          <p>
            Don't have an account?

            <button onClick={() => setPage('signup')}>
              Signup
            </button>
          </p>

        </div>

      )}

      {/* SIGNUP PAGE */}

      {page === 'signup' && (

        <div className="auth-box">

          <h1>Expense Tracker</h1>
          <h2>Create Account</h2>

          <form onSubmit={signup}>

            <input
              type="text"
              placeholder="Full Name"
              value={name}
              onChange={e => setName(e.target.value)}
              required
            />

            <input
              type="email"
              placeholder="Email"
              value={email}
              onChange={e => setEmail(e.target.value)}
              required
            />

            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={e => setPassword(e.target.value)}
              required
            />

            <button type="submit">Register</button>

          </form>

          <p>
            Already registered?

            <button onClick={() => setPage('login')}>
              Login
            </button>
          </p>

        </div>

      )}

      {/* DASHBOARD */}

      {page === 'dashboard' && currentUser && (

        <div className="dashboard">

          <div className="navbar">

            <h2>Expense Tracker</h2>

            <div>
              <span>{currentUser.name}</span>

              <button onClick={logout}>
                Logout
              </button>
            </div>

          </div>

          <h1>Welcome, {currentUser.name}</h1>

          <h2>
            {currentUser.role === 'MANAGER'
              ? 'Manager Dashboard'
              : 'Employee Dashboard'}
          </h2>

          <p>Email: {currentUser.email}</p>
          <p>Role: {currentUser.role}</p>

          {/* EMPLOYEE DASHBOARD */}

          {currentUser.role === 'EMPLOYEE' && (

            <>

              <div className="expense-form">

                <h2>Submit Expense</h2>

                <form onSubmit={submitExpense}>

                  <input
                    type="text"
                    placeholder="Expense Title"
                    value={title}
                    onChange={e => setTitle(e.target.value)}
                    required
                  />

                  <input
                    type="number"
                    placeholder="Amount"
                    value={amount}
                    onChange={e => setAmount(e.target.value)}
                    min="0.01"
                    step="0.01"
                    required
                  />

                  <select
                    value={category}
                    onChange={e => setCategory(e.target.value)}
                  >
                    <option>Food</option>
                    <option>Travel</option>
                    <option>Hotel</option>
                    <option>Other</option>
                  </select>

                  <button type="submit">
                    Submit Expense
                  </button>

                </form>

              </div>

              <h2>My Expenses</h2>

              <table>

                <thead>
                  <tr>
                    <th>Title</th>
                    <th>Amount</th>
                    <th>Category</th>
                    <th>Date</th>
                    <th>Status</th>
                  </tr>
                </thead>

                <tbody>

                  {expenses.map(exp => (

                    <tr key={exp.id}>
                      <td>{exp.title}</td>
                      <td>₹{exp.amount}</td>
                      <td>{exp.category}</td>
                      <td>{exp.appliedDate}</td>
                      <td>{exp.status}</td>
                    </tr>

                  ))}

                </tbody>

              </table>

            </>
          )}

          {/* MANAGER DASHBOARD */}

          {currentUser.role === 'MANAGER' && (

            <>

              <h2>All Employee Expenses</h2>

              <table>

                <thead>
                  <tr>
                    <th>Employee</th>
                    <th>Title</th>
                    <th>Amount</th>
                    <th>Category</th>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Action</th>
                  </tr>
                </thead>

                <tbody>

                  {expenses.map(exp => (

                    <tr key={exp.id}>

                      <td>
                        {exp.user?.name || 'Employee'}
                      </td>

                      <td>{exp.title}</td>
                      <td>₹{exp.amount}</td>
                      <td>{exp.category}</td>
                      <td>{exp.appliedDate}</td>
                      <td>{exp.status}</td>

                      <td>

                        {exp.status === 'PENDING' && (

                          <>
                            <button
                              onClick={() =>
                                updateStatus(exp.id, 'APPROVED')
                              }
                            >
                              Approve
                            </button>

                            <button
                              onClick={() =>
                                updateStatus(exp.id, 'REJECTED')
                              }
                            >
                              Reject
                            </button>
                          </>

                        )}

                      </td>

                    </tr>

                  ))}

                </tbody>

              </table>

              {/* MANAGER TRANSFER */}

              <div className="expense-form">

                <h2>Change Manager</h2>

                <select
                  value={selectedEmployee}
                  onChange={e =>
                    setSelectedEmployee(e.target.value)
                  }
                >

                  <option value="">Select Employee</option>

                  {employees.map(employee => (

                    <option
                      key={employee.id}
                      value={employee.id}
                    >
                      {employee.name} - {employee.email}
                    </option>

                  ))}

                </select>

                <button onClick={changeManager}>
                  Transfer Manager Role
                </button>

              </div>

            </>

          )}

        </div>

      )}

    </div>

  );
}

export default App;
