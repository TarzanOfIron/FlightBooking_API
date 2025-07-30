import React from "react";
import UsersMessage from "./UsersMessage";
import AssistantMessage from "./AssistantMessage";
import axios from "axios";
import { useState } from "react";

type Message = {
  type: "user" | "assistant";
  text: string;
};

type ChatResponse = {
  reply: string; // match this with what your backend returns
};

function Chat() {
  const [input, setInput] = useState("");
  const [messages, setMessages] = useState<Message[]>([]);

  const sendMessage = async () => {
    if (!input.trim()) return;

    const userMessage: Message = { type: "user", text: input };
    setMessages((prev) => [...prev, userMessage]);
    setInput("");

    try {
      const response = await axios.post<string>(
        `http://localhost:8081/api/flightAssistant/chat?question=${encodeURIComponent(input)}`
      );

      console.log("faszkvian")


      const assistantMessage: Message = {
        type: "assistant",
        text: response.data,
      };
      console.log(assistantMessage)
      setMessages((prev) => [...prev, assistantMessage]);
    } catch (error: any) {
      console.error(" Axios error:", error.message);
  if (error.response) {
    console.error(" Server responded with status:", error.response.status);
    console.error(" Response data:", error.response.data);
  } else if (error.request) {
    console.error(" No response received");  
  } else {
    console.error(" Setup error:", error.message);
  }
    }
  };

  return (
    <div className="d-flex flex-fill bg-secondary">
      <div className="col-8 d-flex flex-column rounded-4 mx-auto m-3 bg-dark">
        <div className="messages">
          {messages.map((msg, index) =>
            msg.type === "user" ? (
              <UsersMessage key={index} message={msg.text} />
            ) : (
              <AssistantMessage key={index} response={msg.text} />
            )
          )}
        </div>

        <div className="input-group px-3 pb-2 mt-auto ">
          <input
            type="text"
            value={input}
            className="form-control me-1 rounded-4"
            onChange={(e) => setInput(e.target.value)}
            onKeyDown={(e) => e.key === "Enter" && sendMessage()}
            placeholder="Type your question here..."
          />
          <div className="input-group-append">
            <button
              className="btn bg-white rounded-4"
              type="button"
              onClick={sendMessage}
            >
              Send
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Chat;
