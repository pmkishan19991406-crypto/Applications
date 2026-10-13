
import { useState } from "react";
import "./App.css";

function App() {
  const [topics, setTopics] = useState([]);
  const [topic, setTopic] = useState("");
  const [category, setCategory] = useState("DSA");

  function addTopic() {
    if (topic.trim() === "") {
      return;
    }

    const newTopic = {
      id: Date.now(),
      name: topic,
      category: category,
      completed: false,
    };

    setTopics([...topics, newTopic]);
    setTopic("");
  }

  function completeTopic(id) {
    setTopics(
      topics.map((item) =>
        item.id === id
          ? { ...item, completed: !item.completed }
          : item
      )
    );
  }

  function deleteTopic(id) {
    setTopics(topics.filter((item) => item.id !== id));
  }

  const completedCount = topics.filter(
    (item) => item.completed
  ).length;

  const pendingCount = topics.length - completedCount;

  return (
    <div className="app">
      <h1>Interview Preparation Tracker</h1>

      <div className="progress">
        <h2>My Progress</h2>
        <p>Total Topics: {topics.length}</p>
        <p>Completed: {completedCount}</p>
        <p>Pending: {pendingCount}</p>
      </div>

      <hr />

      <div className="add-section">
        <h2>Add Interview Topic</h2>

        <input
          type="text"
          placeholder="Enter topic name"
          value={topic}
          onChange={(event) => setTopic(event.target.value)}
        />

        <select
          value={category}
          onChange={(event) => setCategory(event.target.value)}
        >
          <option value="DSA">DSA</option>
          <option value="Java">Java</option>
          <option value="React">React</option>
          <option value="Spring Boot">Spring Boot</option>
          <option value="SQL">SQL</option>
        </select>

        <button onClick={addTopic}>Add Topic</button>
      </div>

      <hr />

      <div className="topic-section">
        <h2>My Interview Topics</h2>

        {topics.length === 0 ? (
          <p>No topics added yet.</p>
        ) : (
          <ul>
            {topics.map((item) => (
              <li key={item.id}>
                <span
                  className={item.completed ? "completed" : ""}
                >
                  {item.name} — {item.category}
                </span>

                <button onClick={() => completeTopic(item.id)}>
                  {item.completed ? "Undo" : "Complete"}
                </button>

                <button onClick={() => deleteTopic(item.id)}>
                  Delete
                </button>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
}

export default App;

