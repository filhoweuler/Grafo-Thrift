#!/bin/bash

kill $(ps aux | grep 'Grafo' | awk '{print $2}')