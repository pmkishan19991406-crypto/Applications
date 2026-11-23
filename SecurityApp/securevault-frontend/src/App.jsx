import { useState } from "react";
import "./App.css";

const API_URL = "http://localhost:8080/api";

function App() {
  const [page, setPage] = useState("login");
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [token, setToken] = useState("");
  const [user, setUser] = useState(null);

  async function callApi(path, method, body) {
    const headers = { "Content-Type": "application/json" };
    if (token) {
      headers.Authorization = "Bearer " + token;
    }

    try {
      const response = await fetch(API_URL + path, {
        method: method,
        headers: headers,
        body: body ? JSON.stringify(body) : undefined,
      });
      const data = await response.json();

      if (!response.ok) {
        alert(data.message || "Request failed");
        return null;
      }
      return data;
    } catch (error) {
      alert("Something went wrong: " + error.message);
      return null;
    }
  }

  async function handleRegister(e) {
    e.preventDefault();
    const data = await callApi("/auth/register", "POST", { name, email, password });
    if (data) {
      alert("Registration successful! Please login.");
      setPassword("");
      setPage("login");
    }
  }

  async function handleLogin(e) {
    e.preventDefault();
    const data = await callApi("/auth/login", "POST", { email, password });
    if (data) {
      setToken(data.token);
      setPassword("");
      setPage("dashboard");
    }
  }

  async function handleViewProfile() {
    const data = await callApi("/users/me", "GET");
    if (data) {
      setUser(data);
    }
  }

  async function handleChangePassword(e) {
    e.preventDefault();
    const data = await callApi("/users/change-password", "PUT", {
      currentPassword,
      newPassword,
    });
    if (data) {
      alert(data.message || "Password changed");
      setCurrentPassword("");
      setNewPassword("");
    }
  }

  function handleLogout() {
    setToken("");
    setUser(null);
    setPage("login");
  }

  if (page === "login" || page === "register") {
    const isLogin = page === "login";

    return (
      <div className="container">
        <h1>SecureVault</h1>

        <form onSubmit={isLogin ? handleLogin : handleRegister}>
          <h2>{isLogin ? "Login" : "Register"}</h2>

          {!isLogin && (
            <input
              placeholder="Name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
            />
          )}

          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />

          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />

          <button type="submit">{isLogin ? "Login" : "Register"}</button>
          <button
            type="button"
            onClick={() => setPage(isLogin ? "register" : "login")}
          >
            {isLogin ? "Create Account" : "Back to Login"}
          </button>
        </form>
      </div>
    );
  }

  return (
    <div className="container">
      <h1>SecureVault</h1>
      <h2>Dashboard</h2>

      <button onClick={handleViewProfile}>View Profile</button>
      {user && (
        <p>
          {user.name} - {user.email}
        </p>
      )}

      <h3>Change Password</h3>
      <form onSubmit={handleChangePassword}>
        <input
          type="password"
          placeholder="Current Password"
          value={currentPassword}
          onChange={(e) => setCurrentPassword(e.target.value)}
          required
        />

        <input
          type="password"
          placeholder="New Password"
          value={newPassword}
          onChange={(e) => setNewPassword(e.target.value)}
          required
        />

        <button type="submit">Change Password</button>
      </form>

      <button onClick={handleLogout}>Logout</button>
    </div>
  );
}

export default App;