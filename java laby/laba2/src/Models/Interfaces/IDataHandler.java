package Models.Interfaces;

import java.util.function.Predicate;

public interface IDataHandler<TMenu, VAnswer> {
    Predicate<VAnswer> GetPredicate(TMenu menuChoice);
}
