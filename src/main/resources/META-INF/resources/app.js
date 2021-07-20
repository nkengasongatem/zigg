
const subscriptionURL = "http://localhost:8080/subscribe"

self.addEventListener('push', (event) => {
    if (!(self.Notification && self.Notification.permission === 'granted')) {
        return;
    }

    var data = {};
    if (event.data) {
        data = event.data.json();
    }
    var title = data.title || "Something Has Happened";
    var message = data.message || "Here's something you might want to check out.";
    var icon = "images/zigg.jpeg";

    var notification = new self.Notification(title, {
        body: message,
        tag: 'Stay one step ahead',
        icon: icon
    });

    notification.addEventListener('click', function () {
        if (clients.openWindow) {
            clients.openWindow('https://zigg.com/path/to/more/information');
        }
    });
});

requestNotification();

// functions
function requestNotification() {
    if (!("Notification" in window)) {
        console.log("This browser does not support system notifications");
    } else if (Notification.permission !== "denied") {
        Notification.requestPermission().then(permission => {
            if (permission === "granted") {
                subscribe();
            }
        });
    }
}

async function subscribe() {
    let sw = await navigator.serviceWorker.ready;
    let push = await sw.pushManager.subscribe({
        userVisibleOnly: true,
        applicationServerKey: 'BFmhoL5GQ7S-vIjN4CbGTld3DwZyGuXJcubwosOmRvoL84MacInjWhTmo8606-hmVaWuNJ75LJp7GZE6gFM8ELo'
    });
    console.log(JSON.stringify(push));

    // save subscriber
    const payload = {
        body: JSON.stringify(push),
        method: "POST"
    }

    fetch(subscriptionURL, payload)
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.log(error));
}