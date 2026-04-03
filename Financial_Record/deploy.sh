#!/bin/bash
# ==========================================
# Deployment Script for Financial Record App
# ==========================================

echo "🔄 Pulling latest code from repository..."
# Adjust to your branch if necessary (e.g., git pull origin master)
git pull origin main

echo "🛑 Stopping existing containers..."
docker-compose down

echo "🏗  Building and starting new containers..."
docker-compose up --build -d

echo "🧹 Cleaning up old unused images..."
docker image prune -f

echo "✅ Deployment completed successfully! Backend is running on port 8081."
