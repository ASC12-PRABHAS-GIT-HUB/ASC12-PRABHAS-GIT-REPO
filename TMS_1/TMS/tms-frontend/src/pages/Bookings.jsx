import { useState, useEffect } from 'react';
import apiClient from '../api/apiClient';

const Bookings = () => {
  const [bookings, setBookings] = useState([]);
  const [newBooking, setNewBooking] = useState({ 
    guestId: '', 
    hotelId: '', 
    checkInDate: '', 
    checkOutDate: '', 
    numberOfGuests: '' 
  });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // A simple way to calculate the total price
  const calculateTotalPrice = (checkInDate, checkOutDate, numberOfGuests) => {
    const checkIn = new Date(checkInDate);
    const checkOut = new Date(checkOutDate);
    const timeDifference = checkOut.getTime() - checkIn.getTime();
    const numberOfNights = Math.ceil(timeDifference / (1000 * 3600 * 24));
    const pricePerGuestPerNight = 100; // Assuming a base price for demonstration
    return numberOfNights * numberOfGuests * pricePerGuestPerNight;
  };

  const fetchBookings = async () => {
    try {
      const response = await apiClient.get('/api/bookings');
      setBookings(response.data);
    } catch (err) {
      setError('Failed to fetch bookings.');
      console.error('API Error:', err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchBookings();
  }, []);

  const handleCreate = async (e) => {
    e.preventDefault();
    try {
      const response = await apiClient.post('/api/bookings', {
        ...newBooking,
        numberOfGuests: Number(newBooking.numberOfGuests),
      });
      setBookings([...bookings, response.data]);
      setNewBooking({ guestId: '', hotelId: '', checkInDate: '', checkOutDate: '', numberOfGuests: '' });
    } catch (err) {
      setError('Failed to create new booking.');
      console.error('API Error:', err);
    }
  };

  const handleCancel = async (id) => {
    try {
      await apiClient.put(`/api/bookings/${id}/cancel`);
      setBookings(bookings.map(booking => (booking.id === id ? { ...booking, status: 'Cancelled' } : booking)));
    } catch (err) {
      setError('Failed to cancel booking.');
      console.error('API Error:', err);
    }
  };

  if (loading) return <div className="text-center mt-8">Loading...</div>;
  if (error) return <div className="text-center mt-8 text-red-500">Error: {error}</div>;

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-4xl font-bold mb-6 text-center">Manage Bookings</h1>

      {/* Create Booking Form */}
      <div className="mb-8 p-6 bg-white rounded-lg shadow-md">
        <h2 className="text-2xl font-semibold mb-4">Add a New Booking</h2>
        <form onSubmit={handleCreate} className="space-y-4">
          <div>
            <label htmlFor="guestId" className="block text-sm font-medium text-gray-700">Guest ID</label>
            <input
              type="number"
              id="guestId"
              value={newBooking.guestId}
              onChange={(e) => setNewBooking({ ...newBooking, guestId: e.target.value })}
              required
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
          </div>
          <div>
            <label htmlFor="hotelId" className="block text-sm font-medium text-gray-700">Hotel ID</label>
            <input
              type="number"
              id="hotelId"
              value={newBooking.hotelId}
              onChange={(e) => setNewBooking({ ...newBooking, hotelId: e.target.value })}
              required
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
          </div>
          <div>
            <label htmlFor="checkInDate" className="block text-sm font-medium text-gray-700">Check-in Date</label>
            <input
              type="date"
              id="checkInDate"
              value={newBooking.checkInDate}
              onChange={(e) => setNewBooking({ ...newBooking, checkInDate: e.target.value })}
              required
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
          </div>
          <div>
            <label htmlFor="checkOutDate" className="block text-sm font-medium text-gray-700">Check-out Date</label>
            <input
              type="date"
              id="checkOutDate"
              value={newBooking.checkOutDate}
              onChange={(e) => setNewBooking({ ...newBooking, checkOutDate: e.target.value })}
              required
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
          </div>
          <div>
            <label htmlFor="numberOfGuests" className="block text-sm font-medium text-gray-700">Number of Guests</label>
            <input
              type="number"
              id="numberOfGuests"
              value={newBooking.numberOfGuests}
              onChange={(e) => setNewBooking({ ...newBooking, numberOfGuests: e.target.value })}
              required
              min="1"
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
          </div>
          <button
            type="submit"
            className="w-full px-4 py-2 bg-indigo-600 text-white font-semibold rounded-lg shadow-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
          >
            Create Booking
          </button>
        </form>
      </div>

      {/* Bookings Table */}
      <div className="bg-white rounded-lg shadow-md overflow-hidden">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Booking ID</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Guest ID</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Hotel ID</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Check-in</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Check-out</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
              <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Total Price</th>
              <th scope="col" className="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {bookings.map((booking) => (
              <tr key={booking.id}>
                <td className="px-6 py-4 whitespace-nowrap">{booking.id}</td>
                <td className="px-6 py-4 whitespace-nowrap">{booking.guestId}</td>
                <td className="px-6 py-4 whitespace-nowrap">{booking.hotelId}</td>
                <td className="px-6 py-4 whitespace-nowrap">{booking.checkInDate}</td>
                <td className="px-6 py-4 whitespace-nowrap">{booking.checkOutDate}</td>
                <td className="px-6 py-4 whitespace-nowrap">{booking.status}</td>
                <td className="px-6 py-4 whitespace-nowrap">
                  ${calculateTotalPrice(booking.checkInDate, booking.checkOutDate, booking.numberOfGuests)}
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                  <button onClick={() => handleCancel(booking.id)} className="text-red-600 hover:text-red-900">Cancel</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Bookings;