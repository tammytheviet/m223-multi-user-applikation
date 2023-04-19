import "./App.css"
import { Routes, Route, BrowserRouter } from "react-router-dom";
import Manga from "./components/Manga";
import MangaList from "./components/MangaList";
import MangaUpdate from "./components/MangaUpdate";
import Layout from "./components/Layout";
import Chapter from "./components/Chapter";
import Login from "./components/Login";
import Register from "./components/Register";
import AccountDetails from "./components/AccountDetails";
import Admin from "./components/Admin";

const App = () => {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout />}>
          <Route path="login" element={<Login />} />
          <Route path="register" element={<Register />} />
          <Route path="manga" element={<Manga />} />
          <Route path="mangalist" element={<MangaList />} />
          <Route path="mangaupdate" element={<MangaUpdate />} />
          <Route path="chapter" element={<Chapter />} />
          <Route path="accountdetails" element={<AccountDetails />} />
          <Route path="admin" element={<Admin />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;