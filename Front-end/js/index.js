const sendChatBtn = document.querySelector('.chat-input span');
const chatInput = document.querySelector('.chat-input textarea');
const chatBox = document.querySelector('.chatbox');
const chatbotToggler = document.querySelector('.chatbot-toggler');
const chatbotCloseBtn = document.querySelector('.close-btn');
let userMessage;

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