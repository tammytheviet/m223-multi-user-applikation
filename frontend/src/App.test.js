import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import Manga from './components/Manga';

// Mock fetch API, since we don't want to make a real request in the test
global.fetch = jest.fn().mockImplementation(() =>
    Promise.resolve({
        json: () => Promise.resolve({}),
    })
);

describe('Manga component', () => {
    afterEach(() => {
        jest.clearAllMocks();
    });

    test('should render the component', () => {
        const { getByText } = render(<Manga />);
        expect(getByText('Add Manga')).toBeInTheDocument();
    });

    test('should submit form data on button click', async () => {
        const { getByLabelText, getByText } = render(<Manga />);

        // Fill out the form
        fireEvent.change(getByLabelText('Titel'), { target: { value: 'Naruto' } });
        fireEvent.change(getByLabelText('Genre'), { target: { value: 'Shonen' } });
        fireEvent.change(getByLabelText('Zeichner'), { target: { value: 'Masashi Kishimoto' } });
        fireEvent.change(getByLabelText('Autor'), { target: { value: 'Masashi Kishimoto' } });
        fireEvent.change(getByLabelText('Status'), { target: { value: 'Ongoing' } });
        fireEvent.change(getByLabelText('Veröffentlichungsdatum'), { target: { value: '1999-09-21' } });

        // Click the submit button
        fireEvent.click(getByText('Submit'));

        // Ensure that the data is submitted with the correct values
        expect(global.fetch).toHaveBeenCalledTimes(1);
        expect(global.fetch).toHaveBeenCalledWith('http://localhost:8080/manga', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                titel: 'Naruto',
                genre: 'Shonen',
                zeichner: 'Masashi Kishimoto',
                autor: 'Masashi Kishimoto',
                status: 'Ongoing',
                veröffentlichungsdatum: '1999-09-21',
            }),
        });

        // Ensure that the user is alerted with a success message
        expect(window.alert).toHaveBeenCalledTimes(1);
        expect(window.alert).toHaveBeenCalledWith('New Manga added');
    });

    test('should prevent submission if fields are empty', async () => {
        const { getByText } = render(<Manga />);

        // Click the submit button without filling out the form
        fireEvent.click(getByText('Submit'));

        // Ensure that no network request is made and no alert is displayed
        expect(global.fetch).not.toHaveBeenCalled();
        expect(window.alert).not.toHaveBeenCalled();
    });
});
