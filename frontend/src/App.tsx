
import Header from './components/Header'
import Footer from './components/Footer'
import { BrowserRouter } from 'react-router-dom';
import Chat from './components/Chat';

function App() {


  return (
    <BrowserRouter>
      <div className='d-flex flex-column min-vh-100'>

        <Header/>
        <Chat /> 
        <Footer/>
      </div>

    </BrowserRouter>
  )
}

export default App
