import { render, screen } from '@testing-library/react';
import App from './App';

test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();

  // Additional checks
  const appElement = screen.getByTestId('app');
  expect(appElement).toBeInTheDocument();

  const headerElement = screen.getByTestId('header');
  expect(headerElement).toBeInTheDocument();
});

  test('renders without crashing', () => {
    render(<App />);
    const appElement = screen.getByTestId('app');
    expect(appElement).toBeInTheDocument();
  });

  test('displays the correct title', () => {
    render(<App />);
    const titleElement = screen.getByText(/Welcome to my App/i);
    expect(titleElement).toBeInTheDocument();
  });

  test('displays the correct message', () => {
    render(<App />);
    const messageElement = screen.getByText(/This is a sample message/i);
    expect(messageElement).toBeInTheDocument();
  });
