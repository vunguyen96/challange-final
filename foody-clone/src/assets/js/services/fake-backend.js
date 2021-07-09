const DEFAULT_PRODUCTS = [{
  id: 1,
  name: "Bánh tráng trộn",
  price: "5",
  img: "https://i.ytimg.com/vi/8lNLepEuR8I/maxresdefault.jpg",
  desc: "AirPods are Apple's completely wire-free headphones, which look a bit like the Apple EarPods from older devices, but without the cables. AirPods have Apple-designed tech inside like a special wireless chip called the W1 or H1 (depending on version), an accelerometer for gestures, dual optical sensors, dual beamforming microphones for Siri and phone calls, and a second accelerometer for speech detection."
},
{
  id: 2,
  name: "Cháo hàu sữa",
  price: "7",
  img: "https://image-us.eva.vn/upload/1-2020/images/2020-03-16/1584366195-716-thumbnail_schema_article.jpg",
  desc: "Air Jordan (sometimes abbreviated AJ) is an American brand of basketball shoes, athletic, casual, and style clothing produced by Nike. Founded in Chicago, Air Jordan was created for Hall of Fame basketball player and six-time NBA Finals MVP Michael Jordan during his time with the Chicago Bulls."
},
{
  id: 3,
  name: "Chà xửa chân châu",
  price: "3",
  img: "https://dayphache.edu.vn/wp-content/uploads/2019/02/519cb84dfa56f4e64bd73c0393e49890.jpg",
  desc: "A smartphone is a cell phone that allows you to do more than make phone calls and send text messages. Smartphones can browse the Internet and run software programs like a computer. ... There are thousands of smartphone apps including games, personal-use, and business-use programs that all run on the phone"
}];

export function getProducts(callBack) {
  // TODO: fake an API request
  // remove DEFAULT_PRODUCTS and instead by real respinse from api
  callBack(DEFAULT_PRODUCTS);
}
