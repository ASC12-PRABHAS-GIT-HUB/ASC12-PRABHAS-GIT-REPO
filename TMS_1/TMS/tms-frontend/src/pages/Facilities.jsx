import { useState, useEffect } from 'react';
import apiClient from '../api/apiClient';

const Facilities = () => {
  const [facilities, setFacilities] = useState([]);
  const [newFacility, setNewFacility] = useState({ name: '', description: '', hotelId: '', available: false });
  const [editingFacility, setEditingFacility] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchFacilities = async () => {
    try {
      const response = await apiClient.get('/api/facilities');
      setFacilities(response.data);
    } catch (err) {
      setError('Failed to fetch facilities.');
      console.error('API Error:', err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchFacilities();
  }, []);

  const handleCreate = async (e) => {
    e.preventDefault();
    try {
      const response = await apiClient.post('/api/facilities', newFacility);
      setFacilities([...facilities, response.data]);
      setNewFacility({ name: '', description: '', hotelId: '', available: false });
    } catch (err) {
      setError('Failed to create new facility.');
      console.error('API Error:', err);
    }
  };

  const handleDelete = async (id) => {
    try {
      await apiClient.delete(`/api/facilities/${id}`);
      setFacilities(facilities.filter(facility => facility.id !== id));
    } catch (err) {
      setError('Failed to delete facility.');
      console.error('API Error:', err);
    }
  };

  const handleEditClick = (facility) => {
    setEditingFacility(facility);
    setNewFacility(facility);
  };

  const handleUpdate = async (e) => {
    e.preventDefault();
    try {
      const response = await apiClient.put(`/api/facilities/${editingFacility.id}`, newFacility);
      setFacilities(facilities.map(facility => (facility.id === editingFacility.id ? response.data : facility)));
      setEditingFacility(null);
      setNewFacility({ name: '', description: '', hotelId: '', available: false });
    } catch (err) {
      setError('Failed to update facility.');
      console.error('API Error:', err);
    }
  };

  if (loading) return <div className="text-center mt-8">Loading...</div>;
  if (error) return <div className="text-center mt-8 text-red-500">Error: {error}</div>;

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-4xl font-bold mb-6 text-center">Manage Facilities</h1>
      
      {/* Create/Update Facility Form */}
      <div className="mb-8 p-6 bg-white rounded-lg shadow-md">
        <h2 className="text-2xl font-semibold mb-4">{editingFacility ? 'Edit Facility' : 'Add a New Facility'}</h2>
        <form onSubmit={editingFacility ? handleUpdate : handleCreate} className="space-y-4">
          <div>
            <label htmlFor="name" className="block text-sm font-medium text-gray-700">Facility Name</label>
            <input
              type="text"
              id="name"
              value={newFacility.name}
              onChange={(e) => setNewFacility({ ...newFacility, name: e.target.value })}
              required
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
          </div>
          <div>
            <label htmlFor="description" className="block text-sm font-medium text-gray-700">Description</label>
            <input
              type="text"
              id="description"
              value={newFacility.description}
              onChange={(e) => setNewFacility({ ...newFacility, description: e.target.value })}
              required
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
          </div>
          <div>
            <label htmlFor="hotelId" className="block text-sm font-medium text-gray-700">Hotel ID</label>
            <input
              type="number"
              id="hotelId"
              value={newFacility.hotelId}
              onChange={(e) => setNewFacility({ ...newFacility, hotelId: e.target.value })}
              required
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
          </div>
          <div className="flex items-center">
            <input
              type="checkbox"
              id="available"
              checked={newFacility.available}
              onChange={(e) => setNewFacility({ ...newFacility, available: e.target.checked })}
              className="rounded text-indigo-600 focus:ring-indigo-500"
            />
            <label htmlFor="available" className="ml-2 block text-sm font-medium text-gray-700">Available</label>
          </div>
          <button
            type="submit"
            className="w-full px-4 py-2 bg-indigo-600 text-white font-semibold rounded-lg shadow-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
          >
            {editingFacility ? 'Update Facility' : 'Create Facility'}
          </button>
          {editingFacility && (
            <button
              type="button"
              onClick={() => { setEditingFacility(null); setNewFacility({ name: '', description: '', hotelId: '', available: false }); }}
              className="w-full mt-2 px-4 py-2 bg-gray-500 text-white font-semibold rounded-lg shadow-md hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500"
            >
              Cancel
            </button>
          )}
        </form>
      </div>

      {/* Facilities Table */}
      <div className="bg-white rounded-lg shadow-md overflow-hidden">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Description</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Hotel ID</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Available</th>
              <th scope="col" className="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {facilities.map((facility) => (
              <tr key={facility.id}>
                <td className="px-6 py-4 whitespace-nowrap">{facility.id}</td>
                <td className="px-6 py-4 whitespace-nowrap">{facility.name}</td>
                <td className="px-6 py-4 whitespace-nowrap">{facility.description}</td>
                <td className="px-6 py-4 whitespace-nowrap">{facility.hotelId}</td>
                <td className="px-6 py-4 whitespace-nowrap">{facility.available ? 'Yes' : 'No'}</td>
                <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                  <button onClick={() => handleEditClick(facility)} className="text-indigo-600 hover:text-indigo-900 mr-4">Edit</button>
                  <button onClick={() => handleDelete(facility.id)} className="text-red-600 hover:text-red-900">Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Facilities;