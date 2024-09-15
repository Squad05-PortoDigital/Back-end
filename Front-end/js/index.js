const sendChatBtn = document.querySelector('.chat-input span');
const chatInput = document.querySelector('.chat-input textarea');
const chatBox = document.querySelector('.chatbox');
const chatbotToggler = document.querySelector('.chatbot-toggler');
const chatbotCloseBtn = document.querySelector('.close-btn');
const apiKey = "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";

let userMessage;


// Função para gerar a resposta da API
async function fetchChatGPTResponse(prompt) {
    try {
        const response = await fetch('https://api.openai.com/v1/chat/completions', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${apiKey}`
            },
            body: JSON.stringify({
                model: 'gpt-3.5-turbo',
                messages: [{ role: 'user', content: userMessage }]
            })
        });

        if (!response.ok) {
            // Mostra o erro de resposta
            console.error('Erro na solicitação:', response.statusText);
            return "Erro ao obter a resposta. Tente novamente mais tarde.";
        }

        const data = await response.json();
        return data.choices[0].message.content;
    } catch (error) {
        console.error('Erro na solicitação:', error.message);
        return "Erro na solicitação. Tente novamente.";
    }
}

const createChatLi = (message, className) => {
    const chatLi = document.createElement('li');
    chatLi.classList.add('chat', className);
    let chatContent = className === "outgoing" ? `<p></p>` :  `<span class="material-symbols-outlined">smart_toy</span><p></p>`;
    chatLi.innerHTML = chatContent;
    chatLi.querySelector("p").textContent = message;
    return chatLi;
};

const handleChat = async () => {
    userMessage = chatInput.value.trim();
    if (!userMessage) return;
    chatInput.value = "";
    
    // Adiciona a mensagem do usuário ao chatbox
    chatBox.appendChild(createChatLi(userMessage, "outgoing"));
    chatInput.value = ''; // Limpa o campo de entrada
    chatBox.scrollTo(0, chatBox.scrollHeight);

    // Exibe a mensagem "Digitando..."
    const typingMessage = createChatLi("Digitando...", "incoming");
    chatBox.appendChild(typingMessage);

    // Obtém a resposta da API
    const response = await fetchChatGPTResponse(userMessage);
    
    // Remove a mensagem "Digitando..."
    typingMessage.remove();
    
    // Adiciona a resposta da API ao chatbox
    chatBox.appendChild(createChatLi(response, "incoming"));
    chatBox.scrollTo(0, chatBox.scrollHeight);
};

const handleEnter = () => {
    document.addEventListener('keydown', event => {
        if(event.key === 'Enter') {
            event.preventDefault();
            handleChat();
        }
    });
};

sendChatBtn.addEventListener('click', handleChat);
chatbotToggler.addEventListener('click', () => document.body.classList.toggle('show-chatbot'));
chatbotCloseBtn.addEventListener('click', () => document.body.classList.remove('show-chatbot'));
handleEnter();