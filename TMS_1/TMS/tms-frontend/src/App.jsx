import { Routes, Route, useLocation } from 'react-router-dom';
import Login from './pages/Login';
import Home from './pages/Home';
import Navbar from './components/Navbar';
import Hotels from './pages/Hotels';
import Guests from './pages/Guests';
import Facilities from './pages/Facilities';
import Bookings from './pages/Bookings';

function App() {
  const location = useLocation();
  const isLoginPage = location.pathname === '/login';

  return (
    <>
      {!isLoginPage && <Navbar />}
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/home" element={<Home />} />
        <Route path="/hotels" element={<Hotels />} />
        <Route path="/guests" element={<Guests />} />
        <Route path="/facilities" element={<Facilities />} />
        <Route path="/bookings" element={<Bookings />} />
      </Routes>
    </>
  );
}

export default App;