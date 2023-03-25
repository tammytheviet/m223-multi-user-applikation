import { Outlet } from "react-router-dom";
import AppBar from "./Appbar";

const Layout = () => {
  return(
    <div className="App">
      <div className="content">
        <header className="App-header">
          <AppBar />
          <Outlet/>
        </header>
      </div>
    </div>);
  }

  export default Layout;