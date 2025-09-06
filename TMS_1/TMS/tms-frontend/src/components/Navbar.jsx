import { Link } from 'react-router-dom';

const Navbar = () => {
  return (
    <nav className="bg-gray-800 p-4 text-white">
      <div className="container mx-auto flex justify-between items-center">
        <Link to="/" className="text-xl font-bold">TripMaster</Link>
        <div className="space-x-4">
          <Link to="/hotels" className="hover:text-gray-400">Hotels</Link>
          <Link to="/guests" className="hover:text-gray-400">Guests</Link>
          <Link to="/bookings" className="hover:text-gray-400">Bookings</Link>
          <Link to="/facilities" className="hover:text-gray-400">Facilities</Link>
          <Link to="/login" className="hover:text-gray-400">Logout</Link>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;