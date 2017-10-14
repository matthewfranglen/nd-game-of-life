N Dimensional Game of Life
==========================

This is an implementation of Conway's Game of Life in N Dimensions.

This uses a scala backend for the heavy lifting and a react frontend to display the state.

Running
-------

You need to have [sbt](http://www.scala-sbt.org/index.html) installed to run the backend.
You need to have node / npm / yarn installed to run the frontend.
You need to have docker / docker-compose installed to run the containers.

```bash
cd backend
sbt assembly
```

```bash
cd frontend
yarn install
yarn build:dev
```

```bash
docker-compose up
```

At this point you should be able to browse to [http://localhost:8080](http://localhost:8080) to view the site.

*The frontend is broken at the moment. Such is life*
