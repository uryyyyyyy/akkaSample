
## execute

1 terminal
./activator "run-main com.example.sample.clusterSingleton.Main 2551"

2 terminal
./activator "run-main com.example.sample.clusterSingleton.Main 2552"

3 terminal
./activator "run-main com.example.sample.clusterSingleton.Main 0"

all message will be received by 1.

but when 1 is down, all message will be received by 2.

## explain

in normal setting, oldest actor will be "singleton-master",
and other actors will be "hot-standby"
