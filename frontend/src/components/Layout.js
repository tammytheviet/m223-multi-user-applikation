const Layout = () => {
  return(
    <div className="App">
      <div className="content">
        <header className="App-header">
          <h1>Welcome to the Manga Library</h1>
          <hr/>
          <Outlet/>
        </header>
      </div>
    </div>);
  }

  export default Layout;