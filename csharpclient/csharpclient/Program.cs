using System;
using RestSharp;
namespace csharpclient
{
    class Program
    {
        static bool checkForEmptyString(string toCheck) {
            if(string.IsNullOrEmpty(toCheck)) {
                return false;
            }
            return true;
        }
        static void consoleRun(RestClient client) {
            Console.WriteLine("Please enter the search parameters (Hit enter if you do not wish to search for a parmeter): ");
            Console.WriteLine("event name: ");
            string queryString = "";
            string a = Console.ReadLine();
            string name = "-title=\"" + a + "\"";
            if(checkForEmptyString(a)) {
                queryString += name;
            }
            Console.WriteLine("artist name: ");
            string b = Console.ReadLine();
            string artist = "-name=\"" + b + "\"";
            if(checkForEmptyString(b)) {
                queryString += artist;
            }
            Console.WriteLine("genre: ");
            string c = Console.ReadLine();
            string genre = "-genre=\"" + c + "\"";
            if(checkForEmptyString(c)) {
                queryString += genre;
            }
            Console.WriteLine("location: ");
            string d = Console.ReadLine();
            string location = "-location=\"" + d + "\"";
            if(checkForEmptyString(d)) {
                queryString += location;
            }
            Console.WriteLine("Date from (format: dd.mm.yyyy): ");
            string e = Console.ReadLine();
            string from = "-from=\"" + e + "\"";
            if(checkForEmptyString(e)) {
                queryString += from;
            }
            Console.WriteLine("Date until (format: dd.mm.yyyy): ");
            string f = Console.ReadLine();
            string until = "-until=\"" + f + "\"";
            if(checkForEmptyString(f)) {
                queryString += until;
            }
            Console.WriteLine("Your query String was: " + queryString);
            RestRequest searchRequest = new RestRequest("/events/findByQueryString", Method.GET);
            searchRequest.AddParameter("queryString", queryString);
            IRestResponse response = client.Execute(searchRequest);
            var content = response.Content;
            if(string.IsNullOrEmpty(content)) {
                content = "No results found for search terms!";
            }
            Console.WriteLine(content);
        }
        static void Main(string[] args)
        {
            RestClient client =  new RestClient("http://localhost:8082");
            Console.WriteLine("Welcome to Team B's C# client for Rest.");
            consoleRun(client);        
            Console.WriteLine("Press any key to stop application.");
            Console.ReadKey();
        }
    }
}
