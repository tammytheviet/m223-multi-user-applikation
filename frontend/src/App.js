import "./App.css"
import ReactDOM from "react-dom";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Manga from "./components/Manga";
import MangaList from "./components/MangaList";
import MangaUpdate from "./components/MangaUpdate";
import Layout from "./components/Layout";
import Chapter from "./components/Chapter";

export default function App() {
  return (
    <div className="App">
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
        <Route path="manga" element={<Manga />} />
        <Route path="mangalist" element={<MangaList />} />
        <Route path="mangaupdate" element={<MangaUpdate />} />
        <Route path="chapter" element={<Chapter />} />
        </Route>
      </Routes>
    </BrowserRouter>
    </div>
  );
}

ReactDOM.render(<App />, document.getElementById("root"));