import React, { useEffect, useState } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";

import UserComponent from "./components/user";


interface User {
  id: number;
  name: string;
  username: string;
  email: string;
  phone: string;
  website: string;
  company: { name: string };
  address: { city: string; street: string };
}

const App: React.FC = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [formData, setFormData] = useState({
    name: "",
    username: "",
    email: "",
    phone: "",
    website: "",
    company: "",
    city: "",
    street: "",
  });

 
  useEffect(() => {
    axios.get("https://jsonplaceholder.typicode.com/users")
      .then((response) => setUsers(response.data))
      .catch((error) => console.error("Error fetching users:", error));
  }, []);

 
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleAddUser = (e: React.FormEvent) => {
    e.preventDefault();

  
    const newId = users.length > 0 ? Math.max(...users.map(user => user.id)) + 1 : 1;

    const newUser: User = {
      id: newId,
      name: formData.name,
      username: formData.username,
      email: formData.email,
      phone: formData.phone,
      website: formData.website || "N/A",
      company: { name: formData.company },
      address: { city: formData.city, street: formData.street },
    };

    setUsers([...users, newUser]);
    setFormData({ name: "", username: "", email: "", phone: "", website: "", company: "", city: "", street: "" });
  };

  
  const handleRemove = (id: number) => {
    setUsers(users.filter((user) => user.id !== id));
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center text-primary">User Management</h2>

      
      <div className="card p-3 mt-3">
        <h4 className="text-center text-success">Add User</h4>
        <form onSubmit={handleAddUser}>
          <div className="row">
            <div className="col-md-6">
              <label className="form-label">Name</label>
              <input type="text" className="form-control" name="name" value={formData.name} onChange={handleChange} required />
            </div>
            <div className="col-md-6">
              <label className="form-label">Username</label>
              <input type="text" className="form-control" name="username" value={formData.username} onChange={handleChange} required />
            </div>
            <div className="col-md-6">
              <label className="form-label">Email</label>
              <input type="email" className="form-control" name="email" value={formData.email} onChange={handleChange} required />
            </div>
            <div className="col-md-6">
              <label className="form-label">Phone</label>
              <input type="text" className="form-control" name="phone" value={formData.phone} onChange={handleChange} required />
            </div>
            <div className="col-md-6">
              <label className="form-label">Website</label>
              <input type="text" className="form-control" name="website" value={formData.website} onChange={handleChange} />
            </div>
            <div className="col-md-6">
              <label className="form-label">Company</label>
              <input type="text" className="form-control" name="company" value={formData.company} onChange={handleChange} required />
            </div>
            <div className="col-md-6">
              <label className="form-label">City</label>
              <input type="text" className="form-control" name="city" value={formData.city} onChange={handleChange} required />
            </div>
            <div className="col-md-6">
              <label className="form-label">Street</label>
              <input type="text" className="form-control" name="street" value={formData.street} onChange={handleChange} required />
            </div>
          </div>
          <button type="submit" className="btn btn-success mt-3 w-100">Add User</button>
        </form>
      </div>

      
      <UserComponent userData={users} onRemove={handleRemove} />
    </div>
  );
};

export default App;
