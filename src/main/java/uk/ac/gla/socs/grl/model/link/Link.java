package uk.ac.gla.socs.grl.model.link;

import java.util.Optional;

public interface Link<C> {

    Optional<C> getContribution();

    LinkKind getLinkKind();
}
