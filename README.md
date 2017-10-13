N Dimensional Game of Life
==========================

This is an implementation of Conway's Game of Life in N Dimensions.

This uses a scala backend for the heavy lifting and a react frontend to display the state.

Running
-------

You need to have [sbt](http://www.scala-sbt.org/index.html) installed to run the backend.
You need to have node / npm / yarn installed to run the frontend.

```bash
cd backend
sbt run
```

```bash
cd frontend
yarn install
yarn start
```

You can then browse to [localhost:5000](http://localhost:5000) to view the site.

... well, you could. It doesn't work. There is a problem with the html generation and the webpack dev server. At this point I have had enough.
