BeastTool
=========

Note: This project is being migrated from Google-Code. If any problem is found, please open an issue.

The aim of this project is the development of a system which allows Behavior Driven Development (BDD)
in Multi-Agent Systems (MAS), to make testing practices more accessible and intuitive to everybody.

In one hand, in order to let tests be writable by newcomers and experts alike, system must allow the 
redaction of tests in plain text, because client does not need to have knowledge of our code. This plain
text will be traduced to software later.

The definition of test will be realized with the terminology Given-When-Then, which allows trace an easy 
guide of the behavior of a given scenario when something happened.

In the other hand, due to the complexity of MAS, making unit testing of an agent that needs the interaction 
with others is almost impossible until the whole system is finished. This implies to leave testing issues 
to the end of the project, generating big troubles in case of malfunction. Consequently, its necessary to 
carry out a tool to allow the creation of mock agents and to perform tests during the whole development process.
Therefore another objective of our systems is to include a mocking tool which permits testing continuously.

Definitively, our tool allows the testing of any MAS in the development process, increasing its modularity 
and decreasing its elaboration and testing cost. These tests will be written in plain text so that anyone 
would be able to understand them. 

For further reading, a paper published in ITMAS2012 workshop can be found in: 
http://scholar.google.es/citations?view_op=view_citation&hl=es&user=mT3KgXUAAAAJ&citation_for_view=mT3KgXUAAAAJ:Tyk-4Ss8FVUC

## Wiki ##
In the wiki project, you can find the following pages:

- [[Beast in 5 minutes]]
- [[Tutorial]]
- [[User Manual]]
- [[Developer Manual]]
