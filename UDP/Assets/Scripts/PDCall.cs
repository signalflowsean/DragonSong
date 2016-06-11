using UnityEngine;
using System.Collections;
using System.IO;
using System.Text;
using System.Net.Sockets;

public class PDCall : MonoBehaviour
{

    private TcpClient client;
    private Stream stream;
    private ASCIIEncoding asen;

    void Start()
    {
        client = new TcpClient();
        client.Connect("127.0.0.1", 32000);
        stream = client.GetStream();
        asen = new ASCIIEncoding();
        InvokeRepeating("SoundUpdate", 1, 0.3f);
    }

    void SoundUpdate()
    {
        string msg = "/note " + (60 + (int)(Random.value * 20)) + ";";
        byte[] ba = asen.GetBytes(msg);
        stream.Write(ba, 0, ba.Length);
    }
}