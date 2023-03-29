import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getCurrentUserToken, getUsername } from '../auth.service';

const AccountDetails = () => {
    const navigate = useNavigate();
    const [userData, setUserData] = useState(null);

    useEffect(() => {
        const username = getUsername();

        fetch('http://localhost:8080/api/auth/account', {
            method: 'GET',
            headers: {
                Username: username
            },
        })
            .then((res) => res.json())
            .then((data) => setUserData(data))
            .catch((error) => console.log(error));
    }, []);

    return (
        <div>
            {userData ? (
                <div>
                    <h1>Account Details</h1>
                    <p>Username: {userData.username}</p>
                    <p>Email: {userData.email}</p>
                </div>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    );
}

export default AccountDetails;