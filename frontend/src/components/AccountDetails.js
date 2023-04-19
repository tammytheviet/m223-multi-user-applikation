import React, { useState, useEffect } from 'react';
import { getCurrentUserToken } from '../auth.service';
import AuthService from '../auth.service';

const AccountDetails = () => {
    const [userData, setUserData] = useState(null);
    const [ currentUser, setCurrentUser ] = useState();
    const [ showAdmin, setShowAdmin ] = useState(false);

    useEffect(() => {
        const userToken = getCurrentUserToken();

        fetch('http://localhost:8080/api/auth/account', {
            method: 'GET',
            headers: {
                Authorization: `Bearer ${userToken}`
            }
        })
            .then((res) => res.json())
            .then((data) => setUserData(data))
            .catch((error) => console.log(error));

        const user = AuthService.getCurrentUser();
        if (user) {
            setCurrentUser(user)
            setShowAdmin(user.roles.includes("ROLE_ADMIN"))
        }
    }, []);

    return (
        <div>
            {userData ? (
                <div>
                    <h1>Account Details</h1>
                    <p>Username: {userData.username}</p>
                    <p>Email: {userData.email}</p>
                    <p>Rollen: User
                    {showAdmin && (
                        <span>, Admin</span>
                    )}
                    </p>
                    
                </div>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    );
}

export default AccountDetails;