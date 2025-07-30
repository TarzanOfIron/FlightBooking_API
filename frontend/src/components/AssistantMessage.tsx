import React from 'react'

interface props {
  response: String;
}

function AssistantMessage ({response } : props){
  return (
    <div className='assistant-response d-felx rounded-4 me-auto m-3 p-2 ' style={{ maxWidth: "75%", backgroundColor: "lightGrey"}} >
        <p className='m-auto'> { response }</p>
    </div>
  )
}

export default AssistantMessage
