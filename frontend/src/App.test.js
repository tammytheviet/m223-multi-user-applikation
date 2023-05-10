import { render, screen } from '@testing-library/react';
import App from './App';

  test("Erstellt einen neuen Benutzer.", () => {
    render(<App/>);
    expect(register("testUser", "test@mail.ch", "test1234")).toBeInTheDocument();
  });
