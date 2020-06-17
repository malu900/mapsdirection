import { API_BASE_URL, ACCESS_TOKEN } from "../constants";

const request = (options) => {
  // set header
  const headers = new Headers({
    "Content-type": "application/json",
    "Accept": "application/json"
  });
  // add authorization to header if loggedin
  if (localStorage.getItem(ACCESS_TOKEN)) {
    headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
  }

  const defaults = { headers: headers };
  // javascript method
  // assign header to options props + options props
  // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/assign
  options = Object.assign({}, defaults, options);

  // any url
  return fetch(options.url, options)
    .then(response => 
        response.json().then(json => {
            if(!response.ok) {
                return Promise.reject(json);
            }
            return json;
        })
    );
};

export function signup(signupRequest) {
  return request({
      url: API_BASE_URL + "/auth/signup",
      method: 'POST',
      body: JSON.stringify(signupRequest)
  });
}

export function login(loginrequest) {
    return request({
        url: API_BASE_URL + "/auth/signin",
        method: 'POST',
        body: JSON.stringify(loginrequest)
    })
}

export function getCurrentUser() {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token")
    }
    return request({
        url: API_BASE_URL + "/user/me",
        method: 'GET'
    })
}

export function getUserProfile(username) {
  return request({
      url: API_BASE_URL + "/users/" + username,
      method: 'GET'
  });
}

export function createDirection(directionData) {
    return request({
        url: API_BASE_URL + "/directions",
        method: 'POST',
        body: JSON.stringify(directionData)
    })
}

export function getListDirections() {
    return request({
        url: API_BASE_URL + "/directions",
        method: 'GET'
    })
}

export function getCreatedByDirections(username) {
    return request({
        url: API_BASE_URL + "/users/" + username + "/directions",
        method: 'GET'
    });
}

export function createUserDirection(username, id) {
    return request({
        url: API_BASE_URL + "/users/" + username + "/directions/" + id,
        method: 'GET'
    });
}

export function getFavoriteDirections(username) {
    return request({
        url: API_BASE_URL + "/users/" + username + "/directions/favorites/",
        method: 'GET'
    });
}
