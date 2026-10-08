# Serverless Architecture

Serverless means the cloud provider manages servers while developers deploy small functions. An event, HTTP request or file upload triggers a function; the provider starts it, scales it and charges mainly for execution. AWS Lambda is a common example.

Advantages: less server administration, automatic scaling and pay-per-use. Limitations: cold starts, execution limits, debugging difficulty and vendor lock-in. It suits event-driven tasks such as sending a courier-status notification after an update.

Viva: **Does serverless mean no servers exist?** No; the provider manages them. **What triggers a function?** An event such as an HTTP request or queue message.
