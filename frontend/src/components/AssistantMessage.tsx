import ReactMarkdown from 'react-markdown';
import remarkGfm from "remark-gfm";

interface props {
  response: string;
}

function AssistantMessage ({response } : props) {
  return (
    <div className='assistant-message d-felx rounded-4 me-auto m-3 p-2 ' style={{ maxWidth: "75%", backgroundColor: "lightGrey"}} >
        <ReactMarkdown>
          {response}
        </ReactMarkdown>
    </div>
  )
}

export default AssistantMessage
