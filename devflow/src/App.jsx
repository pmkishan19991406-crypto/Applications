
import { useEffect, useState } from "react";
import "./App.css";

function App() {
  const [bugs, setBugs] = useState(() => {
    const savedBugs = localStorage.getItem("devflow-bugs");
    return savedBugs ? JSON.parse(savedBugs) : [];
  });

  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [priority, setPriority] = useState("Medium");
  const [search, setSearch] = useState("");
  const [statusFilter, setStatusFilter] = useState("All");
  const [priorityFilter, setPriorityFilter] = useState("All");

  useEffect(() => {
    localStorage.setItem("devflow-bugs", JSON.stringify(bugs));
  }, [bugs]);

  function addBug(event) {
    event.preventDefault();

    if (!title.trim()) {
      alert("Please enter a bug title.");
      return;
    }

    const newBug = {
      id: Date.now(),
      title: title.trim(),
      description: description.trim(),
      priority: priority,
      status: "Open",
      createdAt: new Date().toLocaleDateString()
    };

    setBugs((previousBugs) => [...previousBugs, newBug]);

    setTitle("");
    setDescription("");
    setPriority("Medium");
  }

  function updateStatus(id, newStatus) {
    setBugs((previousBugs) =>
      previousBugs.map((bug) =>
        bug.id === id
          ? { ...bug, status: newStatus }
          : bug
      )
    );
  }

  function deleteBug(id) {
    const confirmed = window.confirm(
      "Are you sure you want to delete this bug?"
    );

    if (confirmed) {
      setBugs((previousBugs) =>
        previousBugs.filter((bug) => bug.id !== id)
      );
    }
  }

  const filteredBugs = bugs.filter((bug) => {
    const matchesSearch =
      bug.title.toLowerCase().includes(search.toLowerCase()) ||
      bug.description.toLowerCase().includes(search.toLowerCase());

    const matchesStatus =
      statusFilter === "All" || bug.status === statusFilter;

    const matchesPriority =
      priorityFilter === "All" || bug.priority === priorityFilter;

    return matchesSearch && matchesStatus && matchesPriority;
  });

  const openCount = bugs.filter(
    (bug) => bug.status === "Open"
  ).length;

  const inProgressCount = bugs.filter(
    (bug) => bug.status === "In Progress"
  ).length;

  const resolvedCount = bugs.filter(
    (bug) => bug.status === "Resolved"
  ).length;

  return (
    <main className="app">
      <header className="header">
        <div>
          <h1>DevFlow</h1>
          <p>Developer Bug Tracking System</p>
        </div>
        <span className="project-label">ISSUE MANAGER</span>
      </header>

      <section className="stats">
        <div className="stat-card">
          <span>Total Bugs</span>
          <h2>{bugs.length}</h2>
        </div>

        <div className="stat-card">
          <span>Open</span>
          <h2>{openCount}</h2>
        </div>

        <div className="stat-card">
          <span>In Progress</span>
          <h2>{inProgressCount}</h2>
        </div>

        <div className="stat-card">
          <span>Resolved</span>
          <h2>{resolvedCount}</h2>
        </div>
      </section>

      <section className="panel">
        <h2>Report a Bug</h2>

        <form onSubmit={addBug} className="bug-form">
          <label>
            Bug Title
            <input
              type="text"
              placeholder="Example: Login page crashes"
              value={title}
              onChange={(event) => setTitle(event.target.value)}
            />
          </label>

          <label>
            Description
            <textarea
              placeholder="Describe the problem..."
              value={description}
              onChange={(event) =>
                setDescription(event.target.value)
              }
              rows="3"
            />
          </label>

          <label>
            Priority
            <select
              value={priority}
              onChange={(event) => setPriority(event.target.value)}
            >
              <option value="Low">Low</option>
              <option value="Medium">Medium</option>
              <option value="High">High</option>
            </select>
          </label>

          <button type="submit">+ Add Bug</button>
        </form>
      </section>

      <section className="panel">
        <h2>Manage Bugs</h2>

        <div className="filters">
          <input
            type="text"
            placeholder="Search bugs..."
            value={search}
            onChange={(event) => setSearch(event.target.value)}
          />

          <select
            value={statusFilter}
            onChange={(event) => setStatusFilter(event.target.value)}
          >
            <option value="All">All Statuses</option>
            <option value="Open">Open</option>
            <option value="In Progress">In Progress</option>
            <option value="Resolved">Resolved</option>
          </select>

          <select
            value={priorityFilter}
            onChange={(event) => setPriorityFilter(event.target.value)}
          >
            <option value="All">All Priorities</option>
            <option value="High">High</option>
            <option value="Medium">Medium</option>
            <option value="Low">Low</option>
          </select>
        </div>

        {filteredBugs.length === 0 ? (
          <p className="empty">
            No bugs found. Add a bug or change your filters.
          </p>
        ) : (
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>Bug</th>
                  <th>Priority</th>
                  <th>Status</th>
                  <th>Created</th>
                  <th>Action</th>
                </tr>
              </thead>

              <tbody>
                {filteredBugs.map((bug) => (
                  <tr key={bug.id}>
                    <td>
                      <strong>{bug.title}</strong>
                      <p className="description">
                        {bug.description || "No description"}
                      </p>
                    </td>

                    <td>
                      <span
                        className={`priority ${bug.priority.toLowerCase()}`}
                      >
                        {bug.priority}
                      </span>
                    </td>

                    <td>
                      <select
                        value={bug.status}
                        onChange={(event) =>
                          updateStatus(bug.id, event.target.value)
                        }
                      >
                        <option value="Open">Open</option>
                        <option value="In Progress">In Progress</option>
                        <option value="Resolved">Resolved</option>
                      </select>
                    </td>

                    <td>{bug.createdAt}</td>

                    <td>
                      <button
                        type="button"
                        className="delete-button"
                        onClick={() => deleteBug(bug.id)}
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </section>

      <footer>
        DevFlow · Built with React
      </footer>
    </main>
  );
}

export default App;