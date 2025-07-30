import React from 'react'

const Chat = () => {
  return (
    <div className='d-flex flex-fill bg-secondary'>
        <div className='col-8 d-flex rounded-4 mx-auto m-3 bg-dark'>
            
            <div className="input-group m-2 mt-auto">
                <input type="text" className="form-control me-1 rounded-4" placeholder="Type your question here..."  />
                <div className="input-group-append">
                    <button className="btn bg-white rounded-4" type="button">Send</button>
                </div>
            </div>
        </div>
    </div>
  )
}

export default Chat
