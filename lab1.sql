USE mosorinzakharlab1;
-- SELECT * from chat;

SELECT * from message;

-- find users chats
SELECT distinct user_id 
FROM chat_has_user
ORDER BY user_id;

-- searching message
SELECT CONCAT('time:', time, '     id:', id) 
FROM message
WHERE time BETWEEN '1998-12-01' AND '1999-12-01'  
ORDER BY time ;

-- searching of users
SELECT * 
FROM user
WHERE name LIKE 'V__%';

-- Statistic in messages
SELECT count(*) as Amount_Of_Messages
FROM message;

-- User -> message -> chat (Old way)
SELECT user.name User_Name, chat.name Chat_Name, text, time
FROM message, user, chat
WHERE message.user_id = user.id AND chat_id = chat.id
ORDER BY User_Name, Chat_Name, time;


-- User -> message -> chat (Join way)
SELECT user.name User_Name, chat.name Chat_Name, text, time
FROM message 
	JOIN user 
	ON message.user_id = user.id
    JOIN chat
    ON message.chat_id = chat.id
ORDER BY User_Name, Chat_Name, time;

-- users who didnt write anything
SELECT user.id, name
FROM user LEFT JOIN message ON user.id = message.user_id
WHERE text IS NULL;
