# Base image with Node.js
FROM node:18

# Set working directory
WORKDIR /app

# Copy React application files into the container
COPY ./package.json ./package-lock.json ./
RUN npm install
COPY ./ ./

# Build the React application
RUN npm run build

# Expose React port
EXPOSE 3000

# Command to run the React application
CMD ["npm", "start"]
