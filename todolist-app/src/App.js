import React, { Component } from 'react';

import './index.css';

class TodoApp extends Component {
  state = {
    todos: [],
    newTask: '', // State for the input field to create a new task
    editingId: null, // State to track which todo is being edited
    editingTask: '', // State for the input field to edit a task
  };

  async componentDidMount() {
    try {
      const response = await fetch('/api/v1/todo/list');
      const todos = await response.json();
      this.setState({ todos });
    } catch (error) {
      console.error('Failed to fetch todos:', error);
    }
  }

  // Handle input changes for the new task field
  handleNewTaskChange = (event) => {
    this.setState({ newTask: event.target.value });
  };

  // Handle input changes for the editing task field
  handleEditingTaskChange = (event) => {
    this.setState({ editingTask: event.target.value });
  };

  // CREATE: Add a new todo
  handleCreateTodo = async (event) => {
    event.preventDefault();
    const { newTask, todos } = this.state;
    if (!newTask.trim()) return;

    try {
      const response = await fetch('/api/v1/todo/create', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ task: newTask, completed: false }),
      });
      const newTodo = await response.json();
      this.setState({
        todos: [...todos, newTodo],
        newTask: '', // Clear the input field
      });
    } catch (error) {
      console.error('Failed to create todo:', error);
    }
  };

  // EDIT: Update a todo
  handleUpdateTodo = async (id) => {
  const { editingTask, todos } = this.state;
  if (!editingTask.trim()) return;

  // Find the original todo object to get its 'completed' status
  const originalTodo = todos.find(todo => todo.id === id);
  if (!originalTodo) return;

  // Construct the full object with the updated task
  const updatedTodoData = {
    id: originalTodo.id,
    task: editingTask,
    completed: originalTodo.completed,
  };

  try {
    const response = await fetch(`/api/v1/todo/update/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(updatedTodoData), // Send the complete object
    });

    const updatedTodo = await response.json();
    this.setState({
      todos: todos.map(todo => (todo.id === id ? updatedTodo : todo)),
      editingId: null,
      editingTask: '',
    });
  } catch (error) {
    console.error('Failed to update todo:', error);
  }
};

  // DELETE: Delete a todo
  handleDeleteTodo = async (id) => {
    try {
      await fetch(`/api/v1/todo/delete/${id}`, {
        method: 'DELETE',
      });
      this.setState(prevState => ({
        todos: prevState.todos.filter(todo => todo.id !== id),
      }));
    } catch (error) {
      console.error('Failed to delete todo:', error);
    }
  };

// Toggle the completion status of a todo with confirmation
handleToggleComplete = async (todo) => {
    // Only proceed if the user confirms
    const isConfirmed = window.confirm(`Do you want to confirm marking "${todo.task}" as ${todo.completed ? 'incomplete' : 'complete'}?`);
    
    if (isConfirmed) {
        try {
            // Create a new object with the toggled 'completed' status
            const updatedTodoData = {
                ...todo, // Spreads all original properties (id, task)
                completed: !todo.completed, // Overwrites the completed status
            };

            const response = await fetch(`/api/v1/todo/update/${todo.id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(updatedTodoData), // Send the complete object
            });
            const updatedTodo = await response.json();
            this.setState(prevState => ({
                todos: prevState.todos.map(t => (t.id === todo.id ? updatedTodo : t)),
            }));
        } catch (error) {
            console.error('Failed to toggle completion:', error);
        }
    }
};

  render() {
    const { todos, newTask, editingId, editingTask } = this.state;
    return (
      <div className="todo-app-container">
        <h1>My Todo List</h1>
        {/* CREATE a new todo */}
        <form onSubmit={this.handleCreateTodo}>
          <input
            type="text"
            placeholder="Add a new task"
            value={newTask}
            onChange={this.handleNewTaskChange}
          />
          <button type="submit">Add Task</button>
        </form>

        {/* Display the list of todos */}
        <ul>
          {todos.map(todo => (
            <li key={todo.id}>
              {editingId === todo.id ? (
                // EDIT mode: Show an input field
                <>
                  <input
                    type="text"
                    value={editingTask}
                    onChange={this.handleEditingTaskChange}
                  />
                  <button onClick={() => this.handleUpdateTodo(todo.id)}>Save</button>
                  <button onClick={() => this.setState({ editingId: null, editingTask: '' })}>
                    Cancel
                  </button>
                </>
              ) : (
                // VIEW mode: Show the todo and buttons
                <>
                  <span
                    style={{ textDecoration: todo.completed ? 'line-through' : 'none' }}
                    onClick={() => this.handleToggleComplete(todo)}
                  >
                    {todo.task}
                  </span>
                  <button
                    onClick={() => this.setState({ editingId: todo.id, editingTask: todo.task })}
                  >
                    Edit
                  </button>
                  <button onClick={() => this.handleDeleteTodo(todo.id)}>Delete</button>
                </>
              )}
            </li>
          ))}
        </ul>
      </div>
    );
  }
}

export default TodoApp;