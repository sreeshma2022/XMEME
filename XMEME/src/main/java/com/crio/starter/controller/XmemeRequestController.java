package com.crio.starter.controller;



import com.crio.starter.exceptions.InvalidPostException;
import com.crio.starter.exceptions.PostAlreadyExistException;
import com.crio.starter.exceptions.PostNotFoundException;
import com.crio.starter.exchange.XmemeRequestDto;
import com.crio.starter.exchange.XmemeResponseDto;
import com.crio.starter.service.IXmemeService;
import java.util.List;
import java.util.Map;
//import javax.net.ssl.Status;
import lombok.RequiredArgsConstructor;
// import org.bson.codecs.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class XmemeRequestController {
    @Autowired
    private final IXmemeService xmemeService;


    public static final String BASE_URL = "/memes";

    @GetMapping(BASE_URL)
    public List<XmemeRequestDto> getPosts() {
        return xmemeService.getPosts();
    }

    @PostMapping(BASE_URL)
    public ResponseEntity<?> savePost(@RequestBody XmemeRequestDto request)
            throws InvalidPostException,PostAlreadyExistException{
        long postId;
        try {
            postId = xmemeService.savePost(request);
            XmemeResponseDto response = new XmemeResponseDto(String.valueOf(postId));
            return ResponseEntity.ok(response);
        } catch (PostAlreadyExistException e) {
          
             return ResponseEntity.status(HttpStatus.CONFLICT).body(e);                
        }
        catch(InvalidPostException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
       
        
    }

    @GetMapping(BASE_URL + "/{id}")
    public ResponseEntity<?> getPost(@PathVariable(name = "id") String id)
            throws PostNotFoundException {
                try{
                    return ResponseEntity.ok(xmemeService.getPost(Long.parseLong(id))) ;
                }
                catch (Exception e){ 
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
                }

       
    }

    @PostMapping(BASE_URL + "/{id}")
    public ResponseEntity<?> updatePost(@RequestBody Map<String, Object> updates,
            @PathVariable(name = "id") long id) throws PostNotFoundException, InvalidPostException {
        xmemeService.updatePost(updates, id);
        return ResponseEntity.ok("Post updated");
    }


}
