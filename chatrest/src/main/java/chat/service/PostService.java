package chat.service;


import java.util.ArrayList;
import java.util.List;

import chat.entity.Member;
import chat.repository.MediaFileRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import chat.dto.PostDto;
import chat.entity.Post;
import chat.repository.MemberRepository;
import chat.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final MediaFileRepository mediaFileRepository;

    public List<PostDto> getAllPost() {

        List<PostDto> dtos = new ArrayList();

        for (Post post : postRepository.findAll()) {

            PostDto postDto = new PostDto();

            postDto.setPostId(post.getId());
            postDto.setContent(post.getContent());
            postDto.setNoLikes(post.getNoLikes());

            if (post.getParent() != null) {
                postDto.setParentId(post.getParent().getId());
            }

            if (post.getAuthor() != null) {
                postDto.setAuthorId(post.getAuthor().getId());
            }

            if (post.getFile() != null) {
                postDto.setFileId(post.getFile().getId());
            }
            dtos.add(postDto);
        }
        return dtos;

    }


    public Post add(PostDto postDto) throws Exception {
        System.out.println("postDto ===>  " + postDto);
        Post post = new Post();
        post.setContent(postDto.getContent());

        if (postDto.getAuthorId() != null) {
            try {
                post.setAuthor(memberRepository.findById(postDto.getAuthorId()).get());
            } catch (Throwable t) {
                throw new Exception("Failure to find Author object  in database  == > " + t);
            }

        }

        if (postDto.getFileId() != null) {
            try {
                post.setFile(mediaFileRepository.findById(postDto.getFileId()).get());
            } catch (Throwable t) {
                throw new Exception("Failure to find MediaFile object  in database  == > " + t);
            }

        }


        if (postDto.getParentId() != null) {
            try {

                Long parentId = postDto.getParentId();
                System.out.println("parentId  ======================>  " + postDto.getParentId()  );
                Post parent = postRepository.findById(parentId).get();
                System.out.println("Parent  ==========================>  " + postDto.getParentId()  );
                post.setParent( parent);
                System.out.println("post  ===================>  " + postRepository.findChildPost(parent.getId()) );


//                System.out.println("parent  ========>  " + postDto.getParentId());
//                Long id = postDto.getParentId();
//                System.out.println("parent    ========>  " + id );
//
//
//
//
//
//                System.out.println("after set parent  ========>  "+ parent);

                //System.out.println("Children  ========>  " + parent.getChild());


            } catch (Throwable t) {
                throw new Exception("Failure to find parent object  in database  == > " + t);
            }
        }

        //Post postobj = ;
//        postRepository.findById(postobj.id).get().getParent().getChild().add(postRepository.findById(postobj.id).get());
//        //postobj.getParent().getChild().add(postobj);
        //System.out.println("post  ========>  " + postobj);
        return postRepository.save(post);
    }


    public PostDto getPostById(Long id) {

        Post postObj = postRepository.findById(id).get();

        PostDto postDto = new PostDto();

        postDto.setContent(postObj.getContent());
        postDto.setNoLikes(postObj.getNoLikes());
        postDto.setPostId(postObj.getId());

        // here Do I need to add child posts as comment to list of children
        if (postObj.getParent() != null) {

            postDto.setParentId(postObj.getParent().getId());
        }

        if (postObj.getAuthor() != null) {
            postDto.setAuthorId(postObj.getAuthor().getId());
        }
        return postDto;
    }


    @Transactional
    public boolean deletePostById(Long id, String username) {

        System.out.println("username ========>  " + username + " === >" + postRepository.existsById(id) + "====> id   " + id);
         /*

            delete
            if parentId == null means post
                    if post deleted means all comment need to be deleted to
            if parentId not equal  to null means comments
                    delete a comment only
                    delete comment and its sub-comments
          */

        if (postRepository.existsById(id)) {
            Member member = memberRepository.getByName(username);
            Post post = postRepository.findById(id).get();

            if (post.getAuthor().getId() == member.getId()) {
                // Delete a comment
                if (post.getParent() != null) {
                    System.out.println("Post Parent id is  NOTTTTTTTT NULLLLLLLLLLLLLLL ========>  ");
                    postRepository.deleteByParentId(id);
                    postRepository.deleteById(id);
                    return true;
                }
                // Delete a post
                else {
                    System.out.println("Post Parent id is NULLLLLLLLLLLLLLL ========>  ");
                    postRepository.deleteById(id);
                    //postRepository.deleteByParentId(id);
//                    Set<Post> childPost = new HashSet();
//                    for ( Post p :postRepository.findByParent(post) ){
//
//                        childPost.add(p);
//                        // delete post
//                        postRepository.delete(p);
//                        // remove
//                        childPost.remove(p);
//
//                        for(Post child:childPost){
//                            childPost.add(child);
//                            childPost.addAll(postRepository.findByParent(post));
//                            postRepository.delete(child);
//                        }

                    // System.out.println("username ========>  "+ p);
                    // }

                    return true;
                }

            } else {
                return false;
            }
        }

        return false;
    }
}



