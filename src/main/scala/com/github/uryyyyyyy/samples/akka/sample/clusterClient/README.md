
## execute

1 terminal
./activator "run-main com.example.sample.clusterClient.remote.Main 2551"

2 terminal
./activator "run-main com.example.sample.clusterClient.remote.Main 2552"

3 terminal
./activator "run-main com.example.sample.clusterClient.client.Main"

## explain

each Actor check "cluster.seed-nodes". and try to create cluster.

1 & 2 have args=2551/2552, these will be seed-nodes.

and 3 is cluster client, it check seed-nodes too. and find 1 & 2 cluster.


## one more

you can add more node.

and when 2551 node is down, 
you can add node if you change seed-nodes as existing nodes.
