import { useState, useEffect } from 'react';
import apiClient from '../api/apiClient';

const Guests = () => {
  const [guests, setGuests] = useState([]);
  const [newGuest, setNewGuest] = useState({ firstName: '', lastName: '', email: '', phone: '' });
  const [editingGuest, setEditingGuest] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchGuests = async () => {
    try {
      const response = await apiClient.get('/api/guests');
      setGuests(response.data);
    } catch (err) {
      setError('Failed to fetch guests.');
      console.error('API Error:', err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchGuests();
  }, []);

  const handleCreate = async (e) => {
    e.preventDefault();
    try {
      const response = await apiClient.post('/api/guests', newGuest);
      setGuests([...guests, response.data]);
      setNewGuest({ firstName: '', lastName: '', email: '', phone: '' });
    } catch (err) {
      setError('Failed to create new guest.');
      console.error('API Error:', err);
    }
  };

  const handleDelete = async (id) => {
    try {
      await apiClient.delete(`/api/guests/${id}`);
      setGuests(guests.filter(guest => guest.id !== id));
    } catch (err) {
      setError('Failed to delete guest.');
      console.error('API Error:', err);
    }
  };

  const handleEditClick = (guest) => {
    setEditingGuest(guest);
    setNewGuest(guest);
  };

  const handleUpdate = async (e) => {
    e.preventDefault();
    try {
      const response = await apiClient.put(`/api/guests/${editingGuest.id}`, newGuest);
      setGuests(guests.map(guest => (guest.id === editingGuest.id ? response.data : guest)));
      setEditingGuest(null);
      setNewGuest({ firstName: '', lastName: '', email: '', phone: '' });
    } catch (err) {
      setError('Failed to update guest.');
      console.error('API Error:', err);
    }
  };

  if (loading) return <div className="text-center mt-8">Loading...</div>;
  if (error) return <div className="text-center mt-8 text-red-500">Error: {error}</div>;

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-4xl font-bold mb-6 text-center">Manage Guests</h1>

      {/* Create/Update Guest Form */}
      <div className="mb-8 p-6 bg-white rounded-lg shadow-md">
        <h2 className="text-2xl font-semibold mb-4">{editingGuest ? 'Edit Guest' : 'Add a New Guest'}</h2>
        <form onSubmit={editingGuest ? handleUpdate : handleCreate} className="space-y-4">
          <div>
            <label htmlFor="firstName" className="block text-sm font-medium text-gray-700">First Name</label>
            <input
              type="text"
              id="firstName"
              value={newGuest.firstName}
              onChange={(e) => setNewGuest({ ...newGuest, firstName: e.target.value })}
              required
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
          </div>
          <div>
            <label htmlFor="lastName" className="block text-sm font-medium text-gray-700">Last Name</label>
            <input
              type="text"
              id="lastName"
              value={newGuest.lastName}
              onChange={(e) => setNewGuest({ ...newGuest, lastName: e.target.value })}
              required
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
          </div>
          <div>
            <label htmlFor="email" className="block text-sm font-medium text-gray-700">Email</label>
            <input
              type="email"
              id="email"
              value={newGuest.email}
              onChange={(e) => setNewGuest({ ...newGuest, email: e.target.value })}
              required
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
          </div>
          <div>
            <label htmlFor="phone" className="block text-sm font-medium text-gray-700">Phone</label>
            <input
              type="tel"
              id="phone"
              value={newGuest.phone}
              onChange={(e) => setNewGuest({ ...newGuest, phone: e.target.value })}
              required
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
          </div>
          <button
            type="submit"
            className="w-full px-4 py-2 bg-indigo-600 text-white font-semibold rounded-lg shadow-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
          >
            {editingGuest ? 'Update Guest' : 'Create Guest'}
          </button>
          {editingGuest && (
            <button
              type="button"
              onClick={() => { setEditingGuest(null); setNewGuest({ firstName: '', lastName: '', email: '', phone: '' }); }}
              className="w-full mt-2 px-4 py-2 bg-gray-500 text-white font-semibold rounded-lg shadow-md hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500"
            >
              Cancel
            </button>
          )}
        </form>
      </div>

      {/* Guests Table */}
      <div className="bg-white rounded-lg shadow-md overflow-hidden">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">First Name</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Last Name</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Phone</th>
              <th scope="col" className="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {guests.map((guest) => (
              <tr key={guest.id}>
                <td className="px-6 py-4 whitespace-nowrap">{guest.id}</td>
                <td className="px-6 py-4 whitespace-nowrap">{guest.firstName}</td>
                <td className="px-6 py-4 whitespace-nowrap">{guest.lastName}</td>
                <td className="px-6 py-4 whitespace-nowrap">{guest.email}</td>
                <td className="px-6 py-4 whitespace-nowrap">{guest.phone}</td>
                <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                  <button onClick={() => handleEditClick(guest)} className="text-indigo-600 hover:text-indigo-900 mr-4">Edit</button>
                  <button onClick={() => handleDelete(guest.id)} className="text-red-600 hover:text-red-900">Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Guests;