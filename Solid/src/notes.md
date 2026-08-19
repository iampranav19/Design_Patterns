SOLID + DESIGN PATTERNS + PRINCIPLES CHEAT SHEET
============================================
Repo layout: every topic has a `bad/` (violation) and `good/` (fixed) package,
each with a runnable Main.java. Pattern for every demo:
1. Show the BAD code, run BAD Main -> point out the smell (or the crash).
2. Show the GOOD code, run GOOD Main -> point out what changed and why it's safer.

Every entry below follows the same shape:
Problem  = the specific thing that's wrong in the code
Why bad  = what breaks / what it costs if this ships
Fix      = what the good/ version does differently
Manager  = how to frame it in business terms

Opening line: "None of this is academic — each one maps to a real bug class
we've hit before: rigid code, runtime crashes, fragile refactors, wasted effort."


S — Single Responsibility Principle
------------------------------------
What it is: a class or module should do ONE job, and have only one reason to change.

Files:  src/srp/bad/Employee.java  vs  src/srp/good/{Employee,EmployeeRepository,EmployeeReportGenerator,Main}.java
Run:    java -cp out srp.good.Main   (bad has no Main — it's a design smell, not a crash)

Say: "A class should have ONE reason to change."

Problem:
- Employee.java does 3 unrelated jobs: hold data, save to DB, print a report.

Why bad:
- A report-format tweak and a database change both touch the same file.
- Editing one job risks silently breaking a different job.

Fix:
- Split into 3 classes: Employee (data), EmployeeRepository (save),
  EmployeeReportGenerator (report).
- Each one only changes for its own reason.

Manager takeaway:
- Smaller, safer code reviews — you can review "the report change"
  without touching DB logic.


O — Open/Closed Principle
---------------------------
What it is: you should be able to add new behavior WITHOUT editing existing,
already-tested code — open for extension, closed for modification.

Files:  src/ocp/bad/{Order,ShippingCalculator,Main}.java  vs  src/ocp/good/{ShippingStrategy,StandardShipping,ExpressShipping,InternationalShipping,DroneShipping,ShippingCalculator,Main}.java
Run:    java -cp out ocp.bad.Main
java -cp out ocp.good.Main

Say: "Code should be open for extension, but closed for modification."

Problem:
- ShippingCalculator.calculate() is one long if/else on a shippingType string.

Why bad:
- Adding "Drone" shipping means opening this already-tested class again.
- One new branch risks breaking Standard, Express, and International too.

Fix:
- A ShippingStrategy interface, with one small class per shipping type.
- Calculator just calls strategy.calculate() — it never changes again.
- Adding DroneShipping.java touches zero existing files.

Manager takeaway:
- New features become new files, not risky edits to shared, tested code.
- Less regression risk every time we ship a new option.


L — Liskov Substitution Principle
-----------------------------------
What it is: a subclass must be usable anywhere its parent class is expected,
without the caller noticing anything is different.

Files:  src/lsp/bad/{Payment,CryptoPayment,Main}.java  vs  src/lsp/good/{Payment,Refundable,CardPayment,CryptoPayment,Main}.java
Run:    java -cp out lsp.good.Main   (runs clean)
java -cp out lsp.bad.Main    (CRASHES on purpose — good demo moment)

Say: "A subtype must work anywhere its parent type is expected, without
breaking the caller."

Problem:
- CryptoPayment extends Payment, but crypto can't be refunded.
- It's forced to override refund() and just throw an exception.

Why bad:
- Any code that trusts "every Payment can refund" crashes the moment it
  hits a CryptoPayment — at runtime, in production.

Fix:
- Split into two interfaces: Payment (processPayment) and
  Refundable (refund).
- CryptoPayment only implements what it can honestly do.
- Callers check `instanceof Refundable` instead of assuming.

Manager takeaway:
- This is the "surprise production crash" principle.
- A subclass promising behavior it can't deliver is a ticking time bomb.


I — Interface Segregation Principle
--------------------------------------
What it is: don't force a class to implement methods it doesn't need — split
big, do-everything interfaces into small, focused ones.

Files:  src/isp/bad/{UberDriver,RideDriver,FoodDeliveryDriver,Main}.java  vs  src/isp/good/{RideDriver,FoodDeliveryDriver,UberXDriver,UberEatsDriver,UberProDriver,Main}.java
Run:    java -cp out isp.good.Main   (runs clean)
java -cp out isp.bad.Main    (CRASHES on purpose)

Say: "Don't force a class to implement methods it doesn't use."

Problem:
- UberDriver is one fat interface: ride methods + food-delivery methods.
- A pure ride driver is forced to "implement" delivery methods it can't do,
  just by throwing an exception.

Why bad:
- Calling the full contract on a ride-only driver crashes at runtime —
  same failure shape as the LSP example, caused by a too-broad interface.

Fix:
- Split into RideDriver and FoodDeliveryDriver interfaces.
- UberXDriver implements only rides, UberEatsDriver only deliveries,
  UberProDriver implements both.

Manager takeaway:
- This is Single Responsibility applied to interfaces.
- Keeps contracts honest — no more fake implementations that throw.


D — Dependency Inversion Principle
--------------------------------------
What it is: high-level code should depend on an abstraction (interface), not
on a specific low-level implementation it has to construct itself.

Files:  src/dip/bad/{RecentlyAddedRecommender,TrendingNowRecommender,GenreBasedRecommender,RecommendationEngine,Main}.java
vs  src/dip/good/{RecommendationSource,...same 3 recommenders...,RecommendationEngine,Main}.java
Run:    java -cp out dip.bad.Main
java -cp out dip.good.Main

Say: "High-level modules shouldn't depend on low-level modules — both should
depend on an abstraction."

Problem:
- RecommendationEngine directly creates `new TrendingNowRecommender()`
  inside itself.

Why bad:
- Switching to Genre-Based recommendations means opening and editing
  RecommendationEngine.java itself.
- The engine can't be unit-tested with a fake recommender.

Fix:
- Add a RecommendationSource interface.
- RecommendationEngine takes any RecommendationSource via its constructor.
- Main can wire up Recently Added / Trending Now / Genre-Based without
  touching RecommendationEngine.java at all.

Manager takeaway:
- This is what makes code testable (inject a fake) and configurable
  (swap strategies at runtime — e.g. A/B testing recommendation algorithms).


========= DESIGN PATTERNS =========

Singleton Pattern
------------------
What it is: guarantees a class has exactly ONE instance app-wide, and gives
one global way to access it.

Files:  src/singleton/bad/{DatabaseConnectionManager,Main}.java  vs  src/singleton/good/{DatabaseConnectionManager,Main}.java
Run:    java -cp out singleton.bad.Main
java -cp out singleton.good.Main

Say: "Some things — a connection pool, a config store — should only ever
exist once in the running app."

Problem:
- DatabaseConnectionManager has a public constructor.
- Nothing stops `new DatabaseConnectionManager()` being called anywhere.

Why bad:
- Two "new"s create two independent pools with independent state.
- `pool1 == pool2` is false, and their connection counts drift apart.

Fix:
- Make the constructor private.
- Add a static getInstance() that always returns the same instance.
- `pool1 == pool2` is now true.

Manager takeaway:
- Prevents "why do we have two pools with different state" bugs.
- One shared source of truth, guaranteed.


Factory Pattern
-----------------
What it is: centralizes object-creation logic in ONE place, instead of
scattering "new SomeClass()" calls all over the codebase.

Files:  src/factory/bad/{Logistics,Road,Sea,Air,Main}.java  vs  src/factory/good/{Logistics,Road,Sea,Air,LogisticsFactory,Main}.java
Run:    java -cp out factory.bad.Main
java -cp out factory.good.Main

Say: "Centralize object creation instead of scattering if/else new-ing logic
across the codebase."

Problem:
- Main has an if/else on transportType that directly `new`s up
  Road / Sea / Air itself.

Why bad:
- That same if/else has to be copy-pasted anywhere else a Logistics
  object is needed.
- Every new transport type means finding and editing all those copies.

Fix:
- LogisticsFactory.create(type) becomes the ONLY place that knows about
  Road / Sea / Air.
- Callers only ever talk to Logistics + the factory.

Manager takeaway:
- Adding a transport type is a one-file change, not a codebase-wide
  search-and-replace.


Builder Pattern
------------------
What it is: constructs a complex object step-by-step through a readable,
chained API, instead of one giant constructor with a dozen parameters.

Files:  src/builder/bad/{Pizza,Main}.java  vs  src/builder/good/{Pizza (+ nested Builder),Main}.java
Run:    java -cp out builder.bad.Main
java -cp out builder.good.Main

Say: "When an object has a lot of optional parameters, stop passing them all
positionally."

Problem:
- Pizza has one constructor with 6 positional params, mostly booleans:
  `new Pizza("Large", true, true, false, true, false)`.

Why bad:
- You can't tell what the 3rd `true` means without opening the constructor.
- It's easy to swap two booleans and silently build the wrong pizza.

Fix:
- Build it through a fluent Builder:
  `.cheese().pepperoni().extraCheese().build()`.
- Only call the methods for what you actually want; order doesn't matter.

Manager takeaway:
- Self-documenting construction — no more guessing what the 5th `true` means.


Prototype Pattern
--------------------
What it is: creates new objects by cloning an existing, fully-built one,
instead of building each one from scratch.

Files:  src/prototype/bad/{EmailTemplate,Main}.java  vs  src/prototype/good/{EmailTemplate,Main}.java
Run:    java -cp out prototype.bad.Main    (~1.4s for 3 emails)
java -cp out prototype.good.Main   (~0.5s for 3 emails)

Say: "If building an object is expensive but most copies are nearly
identical, build it once and clone it."

Problem:
- EmailTemplate redoes the full expensive build every time: HTML skeleton,
  branding, CSS — even though the emails are 95% identical.

Why bad:
- That's wasted, repeated work. 3 emails take ~1.4s, even though the only
  real difference between them is the recipient's name.

Fix:
- EmailTemplate implements Cloneable.
- The expensive build runs ONCE to create a prototype.
- Every email after that is `prototype.clone()` + `personalizeFor(name)` —
  ~0.5s for the same 3 emails.

Manager takeaway:
- Real, measurable perf win for "same template, many recipients" work
  (welcome emails, notifications, reports).


Adapter Pattern
------------------
What it is: wraps a class whose interface doesn't match what you need, so it
can be used wherever the expected interface is required.

Files:  src/adapter/bad/{PaymentProcessor,StripeGateway,Main}.java  vs  src/adapter/good/{PaymentProcessor,StripeGateway,StripePaymentAdapter,Main}.java
Run:    java -cp out adapter.bad.Main
java -cp out adapter.good.Main

Say: "Wrap a third-party API's incompatible interface instead of letting its
quirks leak into your business logic."

Problem:
- StripeGateway.makeCharge(int amountInCents) doesn't match our
  PaymentProcessor.pay(double amountInDollars) contract.

Why bad:
- Every caller has to remember to convert dollars to cents itself.
- StripeGateway can't be passed anywhere a PaymentProcessor is expected —
  it just doesn't implement that interface.

Fix:
- StripePaymentAdapter implements PaymentProcessor.
- The cents conversion lives in exactly one place.
- checkout() only ever talks to PaymentProcessor — no idea Stripe exists.

Manager takeaway:
- This is how we swap payment/shipping/analytics vendors later without
  rewriting checkout logic — the adapter absorbs the change.


Facade Pattern
------------------
What it is: provides one simple interface in front of a complex set of
subsystems, so callers don't need to learn how those subsystems work.

Files:  src/facade/bad/{InventoryService,PaymentService,ShippingService,NotificationService,Main}.java
vs  src/facade/good/{...same 4 services...,CheckoutFacade,Main}.java
Run:    java -cp out facade.bad.Main
java -cp out facade.good.Main

Say: "Give a complex subsystem one simple front door instead of making every
caller learn its internals."

Problem:
- Main directly calls Inventory, Payment, Shipping, and Notification
  services itself, in the exact right order, with the right guard checks.

Why bad:
- Every place an order can be placed — web, mobile, admin — has to know
  all 4 subsystems and get that sequence exactly right.
- Miss a step, or check payment before stock, and you've shipped a bug.

Fix:
- CheckoutFacade.placeOrder(...) wraps all 4 calls, in order, behind one
  method.
- Callers just call placeOrder() and get back a boolean.

Manager takeaway:
- One correct implementation of "how to place an order," reused
  everywhere — instead of N copies that can each drift out of sync.


Decorator Pattern
--------------------
What it is: attaches new behavior to an object at runtime by wrapping it,
instead of adding a field/flag to the class or subclassing for every
combination of behavior.

Files:  src/decorator/bad/{Coffee,CoffeeWithMilk,CoffeeWithMilkAndSugar,CoffeeWithMilkAndSugarAndWhip,Main}.java
vs  src/decorator/good/{Beverage,Coffee,CondimentDecorator,Milk,Sugar,Whip,Caramel,Main}.java
Run:    java -cp out decorator.bad.Main
java -cp out decorator.good.Main
(both print the identical result — same combo, different structure)

Say: "Wrap an object to add behavior, instead of subclassing for every
combination."

Problem:
- Every add-on combination gets its own subclass: CoffeeWithMilk,
  CoffeeWithMilkAndSugar, CoffeeWithMilkAndSugarAndWhip — each extending
  the previous one to build up the chain.

Why bad:
- 4 add-ons (Milk, Sugar, Whip, Caramel) → up to 15 non-empty-combo classes;
  a 5th add-on roughly doubles that. This is "class explosion."
- Inheritance only gives linear chains, not free combination: "Milk + Whip"
  (skipping Sugar) needs its own standalone subclass — it can't reuse
  CoffeeWithMilkAndSugarAndWhip at all.

Fix:
- Beverage interface (describe, cost); Coffee is the base implementation.
- CondimentDecorator wraps a Beverage and adds its own cost/description on
  top; Milk/Sugar/Whip/Caramel are each a tiny decorator.
- Combinations are built by composition — `new Whip(new Milk(coffee))` —
  in any order, with zero new classes, and doubling up just works.

Manager takeaway:
- Adding a new add-on (or promo, or topping) is a new file, not N new
  subclasses to cover every combination it can appear in.


Composite Pattern
--------------------
What it is: lets clients treat a single object and a group of objects
uniformly, by giving both the same interface — so trees of objects (a whole
made of parts, some of which are themselves wholes) can be walked with no
type-checking.

Files:  src/composite/bad/{Product,ProductBundle,Main}.java
vs  src/composite/good/{CartItem,Product,ProductBundle,Main}.java
Run:    java -cp out composite.bad.Main
java -cp out composite.good.Main
(both total 2880.0 — good just adds one more nesting level bad can't express)

Say: "Give the part and the whole the same interface, so the client stops
caring which one it's holding."

Problem:
- Product and ProductBundle share no common type. The cart is a
  List<Object>, and every place it's walked needs an
  instanceof Product / instanceof ProductBundle branch per case.
- ProductBundle.addProduct() only accepts a Product — a bundle can never
  contain another bundle.

Why bad:
- A 3rd kind of cart item means a 3rd else-if branch everywhere the cart is
  walked, not just one.
- "Bundle of bundles" (a Mega Bundle wrapping the iPhone Combo and the
  School Kit) is structurally impossible without a near-duplicate class.

Fix:
- CartItem interface (getPrice, display). Product is the leaf; ProductBundle
  is the composite, holding List<CartItem> instead of List<Product>.
- getPrice()/display() recurse through however deep the tree goes — the
  Main loop just calls item.getPrice() / item.display(), no instanceof.
- A ProductBundle can now hold another ProductBundle, so nesting bundles
  falls out for free.

Manager takeaway:
- The cart-walking code (pricing, display, receipts, exports) is written
  once and never needs a new branch when a new composite level is added.


Proxy Pattern
----------------
What it is: a stand-in that implements the same interface as a real object
and sits in front of it, controlling access — checks, caching, filtering,
lazy creation — without the real object or the caller knowing any of that
control logic exists.

Files:  src/proxy/bad/{User,RealVideoDownloader,Main}.java
vs  src/proxy/good/{User,VideoDownloader,RealVideoDownloader,VideoDownloaderProxy,Main}.java
Run:    java -cp out proxy.bad.Main    ("Connecting to CDN..." + a full download print twice)
java -cp out proxy.good.Main   ("Connecting to CDN..." once; 2nd call is a cache hit)

Say: "Put a stand-in in front of the real object instead of stuffing access
control into the object itself."

Problem:
- RealVideoDownloader.downloadVideo() does 4 jobs: downloading, premium
  access checks, region filtering, and caching.
- The cache lives on the instance, so two separately-constructed
  downloaders for "the same request" never share it.

Why bad:
- Downloading is the one job this class should have; the other 3 are
  bolted on, and any other downloader (Music, Document) has to duplicate
  all of them from scratch.
- Nothing can get the raw, unrestricted downloader anymore — not even an
  internal QA tool — because the checks are baked in permanently.
- Setup (the CDN connection) happens the instant `new RealVideoDownloader()`
  runs, even for a call that was only ever going to hit the cache — and
  since the cache is per-instance, it doesn't even hit the cache: Main's
  downloader2 reconnects to the CDN and redownloads a URL downloader1
  already fetched.

Fix:
- VideoDownloader interface; RealVideoDownloader implements it and does
  nothing but download.
- VideoDownloaderProxy also implements VideoDownloader, holding the real
  downloader as a lazily-created field plus the user and a shared cache.
- The proxy checks access, checks the URL, then checks the cache — and only
  constructs/calls the real downloader if all of that passes and nothing
  was cached. Main calls downloadVideo() through the interface either way;
  it can't tell whether it's talking to the real object or the proxy.

Manager takeaway:
- Access control, caching, and rate-limiting become a layer you can add or
  remove without touching the class that does the real work — and expensive
  setup (CDN/DB/network connections) only happens when it's truly needed.


========= OTHER PRINCIPLES (beyond SOLID) =========

DRY — Don't Repeat Yourself
-------------------------------
What it is: every piece of logic should live in exactly ONE place — no
copy-pasting the same rule into multiple classes.

Files:  src/dry/bad/{Order,Invoice,Main}.java  vs  src/dry/good/{TaxCalculator,Order,Invoice,Main}.java
Run:    java -cp out dry.bad.Main    (BUG: prints two different totals)
java -cp out dry.good.Main   (totals match)

Say: "Duplication isn't just ugly — it's a bug waiting to drift out of sync."

Problem:
- Order and Invoice each hardcode their own TAX_RATE and duplicate the
  same tax formula.

Why bad:
- Order's rate got bumped to 8%; Invoice was never updated, still at 5%.
- Same $100 subtotal -> $108 in one place, $105 in the other.

Fix:
- One shared TaxCalculator.calculateTotal().
- Order and Invoice both call it — only one rate to update, ever.

Manager takeaway:
- This is a real production bug pattern, not a style complaint.
- Duplicated logic WILL drift — it's a question of when.


KISS — Keep It Simple, Stupid
----------------------------------
What it is: solve today's problem with the simplest design that works —
don't add structure the problem doesn't call for.

Files:  src/kiss/bad/{FreeShippingRule,MinimumOrderAmountRule,FreeShippingRuleFactory,FreeShippingEvaluator,Main}.java (5 files)
vs  src/kiss/good/{ShippingCalculator,Main}.java (2 files)
Run:    java -cp out kiss.bad.Main
java -cp out kiss.good.Main
(both print the identical result — that's the point)

Say: "Don't build machinery for a question that has a one-line answer."

Problem:
- The bad version answers "is orderTotal >= 50?" using an interface, a
  rule class, a factory, and a generic multi-rule evaluator — for a list
  that only ever holds one rule.

Why bad:
- None of that machinery is needed. It's more code to read, test, and
  maintain, for the exact same answer one comparison already gives you.

Fix:
- ShippingCalculator.isEligibleForFreeShipping(orderTotal) — one static
  method, one line of logic, same output.

Manager takeaway:
- Complexity has a cost even when it "works" — every extra file is more
  surface area for the next dev to get lost in.


YAGNI — You Aren't Gonna Need It
-------------------------------------
What it is: don't build functionality for a requirement you don't have yet —
only build it once it's actually asked for.

Files:  src/yagni/bad/{ReportExporter,CsvReportExporter,PdfReportExporter,XmlReportExporter,ExcelReportExporter,ReportExporterFactory,Main}.java (7 files)
vs  src/yagni/good/{CsvReportExporter,Main}.java (2 files)
Run:    java -cp out yagni.bad.Main
java -cp out yagni.good.Main
(both print the identical result — same as KISS, different root cause)

Say: "Don't build for a requirement that doesn't exist yet — build for the
one you actually have."

Problem:
- The bad version ships Pdf/Xml/Excel exporters "just in case," alongside
  a factory built to support all 4 formats — but only CSV was ever
  requested, and Main only ever uses CSV.

Why bad:
- The 3 extra exporters are untested, unused dead code that still has to
  be kept in sync every time the report's data model changes.
- Real maintenance cost for a requirement nobody has actually asked for.

Fix:
- The good version has exactly one exporter: CsvReportExporter.
- If PDF gets requested next quarter, add PdfReportExporter then.

Manager takeaway:
- KISS vs YAGNI: KISS over-solves the requirement you HAVE; YAGNI solves
  requirements you DON'T have yet.
- Same symptom (bloat), different root cause — both cost real time for
  zero current value.


How to run any demo
---------------------
From the project root:
javac -d out $(find src/<topic> -name "*.java")     # e.g. src/facade
java -cp out <topic>.<bad|good>.Main                # e.g. facade.good.Main

Closing line: "Every 'good' package here is a drop-in replacement for its
'bad' twin — same behavior, but each violation we fixed removes a specific
class of bug: tangled changes (S), risky edits (O), runtime crashes from
broken substitutability (L), fake/throwing implementations (I), untestable
rigid wiring (D), duplicated logic that drifts (DRY), and unnecessary
complexity for problems we do or don't yet have (KISS/YAGNI). The patterns
(Singleton, Factory, Builder, Prototype, Adapter, Facade, Decorator,
Composite, Proxy) are the reusable tools we reach for to get from the bad
column to the good one."