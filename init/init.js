rs.initiate ({
  _id: 'rs0',
  members: [
    {
      _id: 0,
      host: "mongodb01:27017",
      priority: 100
    },
    {
      _id: 1,
      host: "mongodb02:27017",
      priority: 10
    },
    {
      _id: 2,
      host: "mongodb03:27017",
      priority: 1
    }
  ],
});



