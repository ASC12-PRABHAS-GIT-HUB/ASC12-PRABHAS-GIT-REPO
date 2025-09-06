import { useEffect, useState } from 'react';

const Home = () => {
  const [welcomeMessage, setWelcomeMessage] = useState('');

  useEffect(() => {
    const message = sessionStorage.getItem('loginMessage');
    if (message) {
      setWelcomeMessage(message);
      sessionStorage.removeItem('loginMessage');
    }
  }, []);

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-50 font-sans">
      <div className="w-full max-w-2xl p-8 space-y-8 bg-white rounded-lg shadow-lg text-center">
        <h1 className="text-4xl font-bold text-gray-900">Welcome to Your Trip Management System!</h1>
        {welcomeMessage && (
          <p className="text-lg text-green-600">{welcomeMessage}</p>
        )}
        <p className="text-gray-700">
          You are now logged in. This is your main dashboard.
        </p>
        <p className="text-gray-700">
          Now you can navigate to different services from here.
        </p>
      </div>
    </div>
  );
};

export default Home;