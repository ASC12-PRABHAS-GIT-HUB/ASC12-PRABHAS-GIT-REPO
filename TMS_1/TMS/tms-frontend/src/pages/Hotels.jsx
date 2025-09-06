import { useState, useEffect } from 'react';
import apiClient from '../api/apiClient';

const Hotels = () => {
  const [hotels, setHotels] = useState([]);
  const [newHotel, setNewHotel] = useState({ name: '', city: '', pricePerNight: '', rating: '' });
  const [editingHotel, setEditingHotel] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchHotels = async () => {
    try {
      const response = await apiClient.get('/api/hotels');
      setHotels(response.data);
    } catch (err) {
      setError('Failed to fetch hotels.');
      console.error('API Error:', err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchHotels();
  }, []);

  const handleCreate = async (e) => {
    e.preventDefault();
    // Convert pricePerNight and rating to numbers before sending
    const hotelData = {
        ...newHotel,
        pricePerNight: Number(newHotel.pricePerNight),
        rating: Number(newHotel.rating),
    };
    try {
      const response = await apiClient.post('/api/hotels', hotelData);
      setHotels([...hotels, response.data]);
      setNewHotel({ name: '', city: '', pricePerNight: '', rating: '' });
    } catch (err) {
      setError('Failed to create new hotel.');
      console.error('API Error:', err);
    }
  };

  const handleDelete = async (hotelCode) => {
    try {
      await apiClient.delete(`/api/hotels/${hotelCode}`);
      setHotels(hotels.filter(hotel => hotel.hotelCode !== hotelCode));
    } catch (err) {
      setError('Failed to delete hotel.');
      console.error('API Error:', err);
    }
  };

  const handleEditClick = (hotel) => {
    setEditingHotel(hotel);
    setNewHotel(hotel);
  };

  const handleUpdate = async (e) => {
    e.preventDefault();
    // Convert pricePerNight and rating to numbers before sending
    const hotelData = {
        ...newHotel,
        pricePerNight: Number(newHotel.pricePerNight),
        rating: Number(newHotel.rating),
    };
    try {
      const response = await apiClient.put(`/api/hotels/${editingHotel.hotelCode}`, hotelData);
      setHotels(hotels.map(hotel => (hotel.hotelCode === editingHotel.hotelCode ? response.data : hotel)));
      setEditingHotel(null);
      setNewHotel({ name: '', city: '', pricePerNight: '', rating: '' });
    } catch (err) {
      setError('Failed to update hotel.');
      console.error('API Error:', err);
    }
  };

  if (loading) return <div className="text-center mt-8">Loading...</div>;
  if (error) return <div className="text-center mt-8 text-red-500">Error: {error}</div>;

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-4xl font-bold mb-6 text-center">Manage Hotels</h1>
      
      {/* Create/Update Hotel Form */}
      <div className="mb-8 p-6 bg-white rounded-lg shadow-md">
        <h2 className="text-2xl font-semibold mb-4">{editingHotel ? 'Edit Hotel' : 'Add a New Hotel'}</h2>
        <form onSubmit={editingHotel ? handleUpdate : handleCreate} className="space-y-4">
          <div>
            <label htmlFor="name" className="block text-sm font-medium text-gray-700">Hotel Name</label>
            <input
              type="text"
              id="name"
              value={newHotel.name}
              onChange={(e) => setNewHotel({ ...newHotel, name: e.target.value })}
              required
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
          </div>
          <div>
            <label htmlFor="city" className="block text-sm font-medium text-gray-700">City</label>
            <input
              type="text"
              id="city"
              value={newHotel.city}
              onChange={(e) => setNewHotel({ ...newHotel, city: e.target.value })}
              required
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
          </div>
          <div>
            <label htmlFor="pricePerNight" className="block text-sm font-medium text-gray-700">Price Per Night</label>
            <input
              type="number"
              id="pricePerNight"
              value={newHotel.pricePerNight}
              onChange={(e) => setNewHotel({ ...newHotel, pricePerNight: e.target.value })}
              required
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
          </div>
          <div>
            <label htmlFor="rating" className="block text-sm font-medium text-gray-700">Rating</label>
            <input
              type="number"
              id="rating"
              value={newHotel.rating}
              onChange={(e) => setNewHotel({ ...newHotel, rating: e.target.value })}
              required
              step="0.1"
              min="0"
              max="5"
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
          </div>
          <button
            type="submit"
            className="w-full px-4 py-2 bg-indigo-600 text-white font-semibold rounded-lg shadow-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
          >
            {editingHotel ? 'Update Hotel' : 'Create Hotel'}
          </button>
          {editingHotel && (
            <button
              type="button"
              onClick={() => { setEditingHotel(null); setNewHotel({ name: '', city: '', pricePerNight: '', rating: '' }); }}
              className="w-full mt-2 px-4 py-2 bg-gray-500 text-white font-semibold rounded-lg shadow-md hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500"
            >
              Cancel
            </button>
          )}
        </form>
      </div>

      {/* Hotels Table */}
      <div className="bg-white rounded-lg shadow-md overflow-hidden">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Hotel Code</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">City</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Price/Night</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Rating</th>
              <th scope="col" className="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {hotels.map((hotel) => (
              <tr key={hotel.id}>
                <td className="px-6 py-4 whitespace-nowrap">{hotel.id}</td>
                <td className="px-6 py-4 whitespace-nowrap">{hotel.hotelCode}</td>
                <td className="px-6 py-4 whitespace-nowrap">{hotel.name}</td>
                <td className="px-6 py-4 whitespace-nowrap">{hotel.city}</td>
                <td className="px-6 py-4 whitespace-nowrap">${hotel.pricePerNight}</td>
                <td className="px-6 py-4 whitespace-nowrap">{hotel.rating}</td>
                <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                  <button onClick={() => handleEditClick(hotel)} className="text-indigo-600 hover:text-indigo-900 mr-4">Edit</button>
                  <button onClick={() => handleDelete(hotel.hotelCode)} className="text-red-600 hover:text-red-900">Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Hotels;