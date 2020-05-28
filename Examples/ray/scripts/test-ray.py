#!/usr/bin/env python3

import ray

ray.init(memory=100 * 1024 * 1024,
         object_store_memory=80 * 1024 * 1024,
         driver_object_store_memory=20 * 1024 * 1024)

@ray.remote
def f(x):
    return x * x

futures = [f.remote(i) for i in range(1)]
print(ray.get(futures))