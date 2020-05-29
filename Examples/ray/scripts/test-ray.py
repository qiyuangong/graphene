#!/usr/bin/env python3

import ray

ray.init(memory=60 * 1024 * 1024,
         object_store_memory=75 * 1024 * 1024,
         driver_object_store_memory=15 * 1024 * 1024)

@ray.remote
def f(x):
    return x * x

futures = [f.remote(i) for i in range(0)]
print(ray.get(futures))
