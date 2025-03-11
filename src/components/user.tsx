import React from "react";


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

interface Props {
  userData: User[];
  onRemove: (id: number) => void;
}

const UserComponent: React.FC<Props> = ({ userData, onRemove }) => {
  return (
    <div className="mt-4">
      <h3 className="text-center text-info">User's Data</h3>
      <div className="row">
        {userData.map((user) => (
          <div key={user.id} className="col-md-4">
            <div className="card mb-3 shadow-lg border border-secondary bg-white">
              <div className="card-body">
                <h5 className="card-title text-primary">{user.name} ({user.username})</h5>
                <p className="card-text text-dark">
                  <strong>Email:</strong> {user.email} <br />
                  <strong>Phone:</strong> {user.phone} <br />
                  <strong>Website:</strong> 
                  {user.website !== "N/A" ? (
                    <a href={`https://${user.website}`} target="_blank" rel="noopener noreferrer"> {user.website}</a>
                  ) : (
                    " N/A"
                  )}
                  <br />
                  <strong>Company:</strong> {user.company.name} <br />
                  <strong>Address:</strong> {user.address.city}, {user.address.street}
                </p>
                <button className="btn btn-danger w-100 fw-bold" onClick={() => onRemove(user.id)}>
                  Remove
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default UserComponent;
