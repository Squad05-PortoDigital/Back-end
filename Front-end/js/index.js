const sendChatBtn = document.querySelector('.chat-input span');
const chatInput = document.querySelector('.chat-input textarea');
const chatBox = document.querySelector('.chatbox');
const chatbotToggler = document.querySelector('.chatbot-toggler');
const chatbotCloseBtn = document.querySelector('.close-btn');

const options = {
    1: "Justificativa de faltas",
    2: "Solicitação de horas extras",
    3: "Solicitação de férias",
    4: "Solicitação de desligamento",
    5: "Solicitação de benefícios",
};

const createChatLi = (message, className) => {
    const chatLi = document.createElement('li');
    chatLi.classList.add('chat', className);
    let chatContent = className === "outgoing" ? `<p></p>` : `<span class="material-symbols-outlined">smart_toy</span><p></p>`;
    chatLi.innerHTML = chatContent;
    chatLi.querySelector("p").textContent = message;
    return chatLi;
};

function handleMessageFirst(options) {
    const values = Object.keys(options);
    values.forEach(key => {
        chatBox.appendChild(createChatLi(`${key}. ${options[key]}`, "incoming"));   
    });
}

handleMessageFirst(options);

function MsgError() {
    const errorMessage = "Mensagem inválida. Por favor, tente novamente.";
    chatBox.appendChild(createChatLi(errorMessage, "incoming"));
}

let userName = "";
let userRegistration = "";
let userAbsenceDate = "";
let aboutAbsenceUser = "";

const handleChat = () => {
    const userMessage = chatInput.value.trim();

    if (!isNaN(parseInt(userMessage, 10)) && options[userMessage]) {
        chatBox.appendChild(createChatLi(userMessage, "outgoing"));
        chatInput.value = '';
        chatBox.scrollTo(0, chatBox.scrollHeight);

        switch (userMessage) {
            case '1':
                chatBox.appendChild(createChatLi("Para justificar sua falta, por favor, informe seu nome.", "incoming"));
                break;
            case '2':
                chatBox.appendChild(createChatLi("Para solicitar horas extras, informe seu nome.", "incoming"));
                break;
            case '3':
                console.log("Ação específica para a chave 3.");
                break;
            default:
                console.log("Ação padrão para chaves não específicas.");
        }
    }

    else if (userName === "" && userMessage.length > 0) {
        chatBox.appendChild(createChatLi(userMessage, "outgoing"));
        userName = userMessage;
        chatBox.appendChild(createChatLi(`Obrigado, ${userName}! Agora, por favor, informe sua matrícula.`, "incoming"));
        chatInput.value = '';
        chatBox.scrollTo(0, chatBox.scrollHeight);
    }

    else if (userName !== "" && userRegistration === "" && userMessage.length > 0) { 
        userRegistration = userMessage;
        chatBox.appendChild(createChatLi(userMessage, "outgoing"));
        chatBox.appendChild(createChatLi(`Obrigado, ${userName}! Sua matrícula é ${userRegistration}. Agora, por favor, informe a data da falta (formato: YYYY-MM-DD).`, "incoming"));
        chatInput.value = '';
        chatBox.scrollTo(0, chatBox.scrollHeight);
    }

    else if (userName !== "" && userRegistration !== "" && userAbsenceDate === "" && userMessage.length > 0) {
        userAbsenceDate = userMessage;
        chatBox.appendChild(createChatLi(userMessage, "outgoing")); // Mostra a data do usuário
        chatBox.appendChild(createChatLi(`Sua falta no dia ${userAbsenceDate} foi registrada com sucesso. Por favor, agora nos informe o motivo da sua ausência em ${userAbsenceDate}.`, "incoming"));
        chatInput.value = '';
        chatBox.scrollTo(0, chatBox.scrollHeight);
    }

    else if (userName !== "" && userRegistration !== "" && userAbsenceDate !== "" && aboutAbsenceUser === "" && userMessage.length > 0) {
        aboutAbsenceUser = userMessage; // Armazena o motivo da falta
        chatBox.appendChild(createChatLi(userMessage, "outgoing")); // Mostra o motivo
        chatBox.appendChild(createChatLi(`Obrigado! Registramos o seguinte: \nNome: ${userName}, \nMatrícula: ${userRegistration}, \nData da Falta: ${userAbsenceDate}, \nMotivo: ${aboutAbsenceUser}.`, "incoming"));
        chatInput.value = '';
        chatBox.scrollTo(0, chatBox.scrollHeight);
    }
};

const handleEnter = () => {
    document.addEventListener('keydown', event => {
        if (event.key === 'Enter') {
            event.preventDefault();
            handleChat();
        }
    });
};

sendChatBtn.addEventListener('click', handleChat);
chatbotToggler.addEventListener('click', () => document.body.classList.toggle('show-chatbot'));
chatbotCloseBtn.addEventListener('click', () => document.body.classList.remove('show-chatbot'));
handleEnter();
