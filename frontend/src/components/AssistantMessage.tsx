import React from 'react'
import ReactMarkdown from 'react-markdown';


interface props {
  response: string;
}

function AssistantMessage ({response } : props){
  return (
    <div className='assistant-response d-felx rounded-4 me-auto m-3 p-2 ' style={{ maxWidth: "75%", backgroundColor: "lightGrey"}} >
        <ReactMarkdown>{response}</ReactMarkdown>
    </div>
  )
}

export default AssistantMessage
