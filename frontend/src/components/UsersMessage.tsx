import React from 'react'

interface props {
  message: String;
}

function UsersMessage ({ message }: props) {
  return (
    <div className='user-message d-felx rounded-4 ms-auto m-3 p-2 ' style={{ maxWidth: "75%", backgroundColor: "darkGrey"}} >
        <p className='m-auto'>{ message }</p>
    </div>
  )
}

export default UsersMessage
