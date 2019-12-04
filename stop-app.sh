#!/bin/bash
lsof -ti:9090 | xargs kill &&
lsof -ti:9091 | xargs kill &&
lsof -ti:9092 | xargs kill &&
lsof -ti:9093 | xargs kill &&
lsof -ti:9094 | xargs kill &&
lsof -ti:9095 | xargs kill &&
lsof -ti:9096 | xargs kill &&
lsof -ti:9097 | xargs kill &&
lsof -ti:9098 | xargs kill &&
lsof -ti:9011 | xargs kill &&
rm -rf */nohup.out